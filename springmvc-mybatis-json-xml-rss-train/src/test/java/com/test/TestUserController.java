package com.test;

import com.test.base.TestControllerBase;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * UserController测试类
 */
public class TestUserController extends TestControllerBase {

    @Test
    public void testIndex() throws Exception {
        ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/showUser/1")
                //.param("colortop", "FF0000")
                //.param("colorbottom", "00FF00")
        ).andExpect(status().isOk())
         .andExpect(model().attributeExists("user"));
        MvcResult mr = ra.andReturn();
        String result = mr.getResponse().getContentAsString();
        System.out.print(result);
    }

    @Test
    public void testUserJson()throws Exception{
        ResultActions ra=this.mockMvc.perform(MockMvcRequestBuilders
                .get("/users.json")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json;charset=utf-8"));

        MvcResult mr=ra.andReturn();
        System.out.print(mr.getResponse().getContentAsString());
    }
    @Test
    public void testUserXml()throws  Exception{
         ResultActions ra=this.mockMvc.perform(MockMvcRequestBuilders
                 .get("/user.xml")
                 .contentType(MediaType.APPLICATION_XML)
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
