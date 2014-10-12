import com.tang.controller.UserController;
import com.tang.service.IUserService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-servlet.xml", "classpath:spring-config.xml"})
@WebAppConfiguration
public class TestControllerBase {

    @Autowired
    private WebApplicationContext wac;   //忽略错误提示“Could not autowire. No beans of 'WebApplicationContext' type found”

    @Autowired
    private UserController userController;//你要测试的Controller

    @Autowired
    protected IUserService userService;


    protected MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
}
