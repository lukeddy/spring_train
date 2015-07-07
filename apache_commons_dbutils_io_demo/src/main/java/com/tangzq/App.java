package com.tangzq;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Apache commons DBUtils,IO测试示例
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Connection conn = null;
        String jdbcURL = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8";
        String jdbcDriver = "com.mysql.jdbc.Driver";
        try {
            DbUtils.loadDriver(jdbcDriver);
            conn = DriverManager.getConnection(jdbcURL, "root", "123456");
            QueryRunner qRunner = new QueryRunner();
            //Map方式处理结果
            /** List lMap = (List) qRunner.query(conn,"select title,authors  from book", new MapListHandler());
             System.out.println("title ------------- author");
             for (int i = 0; i < lMap.size(); i++) {
             Map valuesMap = (Map) lMap.get(i);
             System.out.println(valuesMap.get("title")+"-------------"+valuesMap.get("author"));
             }**/

            //采用Bean方式处理结果
            List lBeans = (List) qRunner.query(conn," select title,author from book ", new BeanListHandler(Book.class));
            System.out.println("title ------------- author ");
            for (int i = 0; i < lBeans.size(); i++) {
                Book book = (Book) lBeans.get(i);
                System.out.println(book.getTitle ()+"-------------"+ book.getAuthor());
            }

            //TODO 将查询出来的数据保存到文件中
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }

    }
}
