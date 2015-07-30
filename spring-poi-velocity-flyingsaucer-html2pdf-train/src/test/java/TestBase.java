import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: tzq
 * Date: 13-3-13
 * Time: 下午6:38
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring-config.xml","classpath:spring-email.xml"})
@ContextConfiguration(locations = {"classpath:spring-application.xml"})
public class TestBase extends AbstractJUnit4SpringContextTests {
}
