package com.tang.monitor;

import com.tang.entity.TException;
import com.tang.service.IExceptionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by Administrator on 2014/10/23.
 */
public class MonitorBeanAspect {
    private static Log log = LogFactory.getLog(MonitorBeanAspect.class);

    @Autowired
   private IExceptionService exceptionService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * SpringBean方法调用通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    public Object interceptCall(ProceedingJoinPoint pjp) throws Throwable {
        String clazzString = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String fullPath = clazzString + "." + methodName;
        int flag = clazzString.indexOf("$");
        if (flag < 0)
            log.info("开始业务处理[" + methodName + "];全路径[" + fullPath + "]");
        long time = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        time = System.currentTimeMillis() - time;
        if (flag < 0)
            log.info("结束业务处理[" + methodName + "];耗时:" + time + "毫秒;全路径[" + fullPath + "]");
        return retVal;
    }


    /**
     * SpringBean方法异常通知 synchronized:标记为同步方法主要是为处理开启切面监控时候造成死锁的问题.
     *
     * @param jp
     * @param ex
     */
    public synchronized void interceptException1(JoinPoint jp, Throwable ex) {
        String clazzString = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        String fullPath = clazzString + "." + methodName;
        int flag = clazzString.indexOf("$");
        if (flag < 0) {
            log.info("业务处理时发生了异常:[" + fullPath + "]");
            //使用JPA的方式将异常信息存入数据库，这种方式单元测试可以成功，但是在controller里边测试就不行了
            TException tex=new TException();
            tex.setClazz(clazzString);
            tex.setActiveTime(Calendar.getInstance().getTime());
            tex.setMethodName(methodName);
            tex.setException(ex.getMessage());
            Boolean isSaved= exceptionService.add(tex);
            log.info("异常信息保存状态:"+isSaved);
        }
    }

    /**
     * SpringBean方法异常通知 synchronized:标记为同步方法主要是为处理开启切面监控时候造成死锁的问题.
     *
     * @param jp
     * @param ex
     */
    public synchronized void interceptException(JoinPoint jp, Throwable ex) {
        String clazzString = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        String fullPath = clazzString + "." + methodName;
        int flag = clazzString.indexOf("$");
        if (flag < 0) {
            log.info("业务处理时发生了异常:[" + fullPath + "]");
            //使用JdbcTemplate将异常信息存入数据库
            String insertSql = "	INSERT INTO texception (clazz, methodname, activetime, exception) VALUES (?,?,?,?)";
            jdbcTemplate.update(insertSql,clazzString,methodName,Calendar.getInstance().getTime(),ex.getMessage());
        }
    }


    /**
     * SpringBean方法异常通知 synchronized:标记为同步方法主要是为处理开启切面监控时候造成死锁的问题.
     *
     * @param jp
     * @param ex
     */
    public synchronized void interceptException2(JoinPoint jp, Throwable ex) {
        String clazzString = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        String fullPath = clazzString + "." + methodName;
        int flag = clazzString.indexOf("$");
        if (flag < 0) {
            log.info("业务处理时发生了异常:[" + fullPath + "]");
            //使用原生JDBC将异常信息存入数据库
            PreparedStatement statement = null;
            java.sql.Connection connection=null;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shirodemo?useUnicode=true&characterEncoding=UTF-8", "root", "");
                System.out.println("连接成功！");
                connection.setAutoCommit(false);//禁止自动提交，设置回滚点
                String inserSql = "	INSERT INTO texception (clazz, methodname, activetime, exception) VALUES (?,?,?,?)";
                statement = connection.prepareStatement(inserSql);
                statement.setString(1, clazzString);
                statement.setString(2, methodName);
                statement.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
                statement.setString(4, ex.getMessage());
                statement.executeUpdate();
                connection.commit(); //事务提交
                log.warn("出现异常了，异常信息保存数据库状态");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }
    }
}
