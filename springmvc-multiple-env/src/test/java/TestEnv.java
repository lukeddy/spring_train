import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Administrator on 2015/10/20.
 */
public class TestEnv extends TestBase {

    @Value("${appname}")
    private String appname;

    @Test
    public void testEnv(){
        System.out.println(appname);
    }

}
