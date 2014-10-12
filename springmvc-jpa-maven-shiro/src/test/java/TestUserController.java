import com.tang.controller.UserController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * UserController测试类
 */
public class TestUserController extends TestControllerBase {

    @Autowired
    private UserController userController;

    @Test
    public void testIndex() throws Exception {
        ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/")
                .accept(MediaType.TEXT_HTML)
                //.param("colortop", "FF0000")
                //.param("colorbottom", "00FF00")
        ).andExpect(status().isOk());
        MvcResult mr = ra.andReturn();
        String result = mr.getResponse().getContentAsString();
        System.out.print(result);
    }

    @Test
    public void testIndex2() throws Exception {
        ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/")
                .accept(MediaType.TEXT_HTML)
        ).andExpect(status().isOk());
        //.andExpect(content().contentType(MediaType.ALL));
        MvcResult mr = ra.andReturn();
        ModelAndView modelAndView = mr.getModelAndView();
        System.out.println(modelAndView.getViewName());
        assertEquals("user/userList", modelAndView.getViewName());
    }

    @Test
    public void testGetJson() throws Exception {
        ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders
                .get("/getJson"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
        MvcResult mr = ra.andReturn();
        System.out.println(mr.getResponse().getContentAsString());
    }

    @Test
    public void testGetJsonByUserId() throws Exception {
        ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders
                .get("/getJsonById/12"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        MvcResult mr = ra.andReturn();
        System.out.print(mr.getResponse().getContentAsString());
        assertNotNull(mr.getResponse().getContentAsString());
    }


    @Test
    public void testDelUser() throws Exception {
        ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/del/15"))
                .andExpect(status().isMovedTemporarily())
                /*.andExpect(content().contentType("text/html"))*/;

        MvcResult mr = ra.andReturn();
        ModelAndView modelAndView = mr.getModelAndView();
        assertSame("redirect:/", modelAndView.getViewName());
    }

    /**
     * 测试查看用户详情
     *
     * @throws Exception
     */
    @Test
    public void testUserInfo() throws Exception {
        ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/user/16"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("user/userInfo"))
                .andExpect(model().attributeExists("userInfo"));

        MvcResult mr = ra.andReturn();
        assertSame("user/userInfo", mr.getModelAndView().getViewName());

    }

    /**
     * 测试添加用户信息
     * 说明：这里没有对文件进行测试
     * 结果：work
     *
     * @throws Exception
     */
    @Test
    public void testAddUser() throws Exception {
        ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders.post("/addUser")
                .param("account", "3333")
                .param("name", "ttt")
                .param("password", "123aaa")
                .param("chapter", "Hello this is a chapter text"));

        MvcResult mr = ra.andReturn();
        assertSame("redirect:/", mr.getModelAndView().getViewName());
        //assertNotNull(userService.findByUserName("ttt"));
    }


    /**
     * 测试实体类带有文件上传
     * 说明: MockMvcRequestBuilders.fileUpload对CommonsMultipartFile不起作用，这里的commonFile就是该类型的
     * 结果：No work
     * @throws Exception
     */
    //    @Test
    //    public void testAddUser2()throws  Exception{
    //        String filePath="C://springmvc-jpa-maven//src//test//java//aaa.jpg";
    //
    //        final FileInputStream fis = new FileInputStream(filePath);
    //        MockMultipartFile multipartFile = new MockMultipartFile("commonFile","aaa.jpg","image/jpeg",fis);
    //
    //        ResultActions ra=this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/addUser")
    //                .file(multipartFile)
    //                .param("account", "3333")
    //                .param("name", "aaw")
    //                .param("password", "123aaa")
    //                .param("chapter", "Hello this is a chapter text"));
    //
    //        MvcResult mr=ra.andReturn();
    //        assertSame("redirect:/",mr.getModelAndView().getViewName());
    //        assertNotNull(userService.findByUserName("aaw"));
    //    }

    /**
     * 测试实体类带有文件上传
     * 说明： MockMvcRequestBuilders.fileUpload对MultipartFile起作用,这里的myFile就是该类型的
     * 结果：work
     *
     * @throws Exception
     */
    @Test
    public void testAddUser3() throws Exception {
        String filePath = "C://springmvc-jpa-maven//src//test//java//aaa.jpg";

        final FileInputStream fis = new FileInputStream(filePath);
        MockMultipartFile multipartFile = new MockMultipartFile("myFile", "aaa.jpg", "image/jpeg", fis);

        ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/addUser")
                .file(multipartFile)
                .param("account", "3333")
                .param("name", "gfgf")
                .param("password", "123aaa")
                .param("chapter", "Hello this is a chapter text"));

        MvcResult mr = ra.andReturn();
        assertSame("redirect:/", mr.getModelAndView().getViewName());
        //assertNotNull(userService.findByUserName("gfgf"));
    }

    /**
     * 这个访问有问题
     * 结果：No work
     * @throws Exception
     */
    //    @Test
    //    public void testAddUser4()throws  Exception{
    //        String filePath="C://springmvc-jpa-maven//src//test//java//aaa.jpg";
    //        final FileInputStream fis = new FileInputStream(filePath);
    //        MockMultipartFile multipartFile = new MockMultipartFile("myFile","aaa.jpg","image/jpeg",fis);
    //        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest() ;
    //
    //        request.addFile(multipartFile);
    //        request.setMethod("POST");
    //        request.setContentType("multipart/form-data");
    //        request.addHeader("Content-type", "multipart/form-data");
    //        request.setRequestURI("/addUser");
    //        request.addParameter("account", "3333");
    //        request.addParameter("name", "dgdfg") ;
    //        request.addParameter("password", "123aaa");
    //        request.addParameter("chapter", "Hello this is a chapter text");
    //
    //        RequestMappingHandlerAdapter reqAdapter= new RequestMappingHandlerAdapter();
    //        final ModelAndView handle= reqAdapter.handle(request, new MockHttpServletResponse(), this.userController);
    //    }

    /**
     * 测试单个文件上传,使用MultipartFile处理
     * 结果：Work
     *
     * @throws Exception
     */
    @Test
    public void testFileUpload() throws Exception {
        String filePath = "C://springmvc-jpa-maven//src//test//java//aaa.jpg";
        File file = new File(filePath);
        final FileInputStream fis = new FileInputStream(filePath);
        MockMultipartFile multipartFile = new MockMultipartFile("myFile", "aaa.jpg", "image/jpeg", fis);
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/uploadFile")
                //.file("myFile",FileUtils.readFileToByteArray(file) ))
                .file(multipartFile))
                .andExpect(model().attribute("message", "File '" + file.getName() + "' uploaded successfully"));
    }

    /**
     * 测试单个文件上传，使用CommonsMultipartFile处理
     * 结果：No Work
     * @throws Exception
     */
    //    @Test
    //    public void testCommonFileUpload() throws Exception{
    //        String filePath="C://springmvc-jpa-maven//src//test//java//aaa.jpg";
    //        File file=new File(filePath);
    //        final FileInputStream fis = new FileInputStream(filePath);
    //        MockMultipartFile multipartFile = new MockMultipartFile("commonFile","aaa.jpg","image/jpeg",fis);
    //        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/uploadCommonFile")
    //                //.file("myFile",FileUtils.readFileToByteArray(file) ))
    //                .file(multipartFile))
    //                .andExpect(model().attribute("message", "File '"+file.getName()+"' uploaded successfully"));
    //    }

    @Test
    public void testUserXml()throws  Exception{
        ResultActions ra=this.mockMvc.perform(MockMvcRequestBuilders
                .get("/userXml")
        ).andExpect(content().contentType("application/xml;charset=utf-8"));

        System.out.println(ra.andReturn().getResponse().getContentAsString());
    }

    /**
     * 注：使用这个方法可能测不出结果，通过浏览器可以测出来
     * @throws Exception
     */
    @Test
    public void testUserRss()throws  Exception{
        ResultActions ra=this.mockMvc.perform(MockMvcRequestBuilders
                .get("/rssfeed")
        );

        System.out.println(ra.andReturn().getResponse().getContentAsString());
    }
}
