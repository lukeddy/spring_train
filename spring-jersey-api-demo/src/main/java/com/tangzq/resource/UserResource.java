package com.tangzq.resource;

import com.tangzq.service.HelloService;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.mrbean.MrBeanModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;

@Component
@Path("/user")
public class UserResource {

    @Autowired
    private HelloService helloService;

    /**
     * 测试地址：http://localhost:8080/rest/user/users/aaa
     * @param username
     * @return
     */
    @Path("/users/{username}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser(@PathParam("username") String username) {
        System.out.print("username:{}"+ username);
        return username+" say: "+helloService.getMsg();
    }

    /**
     * JSON数据响应
     * @return
     * @throws IOException
     */
    @Path("/users/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUser() throws IOException {
        HashMap<String,Object> data=new HashMap<String,Object>();
        data.put("username","I am jone");
        data.put("age",20);
        data.put("email","hell@example.com");
        return Response.ok(getObjectMapper().writeValueAsString(data)).build();
    }

    /**
     *测试地址：post方式->http://localhost:8080/rest/user/save  参数：id=123&username=adsfs
     * @param id
     * @param username
     * @param request
     * @param response
     */
    @Path("save")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String saveUser(@FormParam("id") String id,
                         @FormParam("username") String username,
                         @Context HttpServletRequest request,
                         @Context HttpServletResponse response) {
        System.out.print("id:{}, username:{}"+id+","+username);
        System.out.print("request:{}, response:{}"+request+","+response);

        return "saved user:"+id+","+username;
    }


    //TODO 添加put,delete测试示例
    //TODO 添加数据库操作


    public ObjectMapper getObjectMapper(){
        ObjectMapper   mapper = new ObjectMapper().enable(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY).enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
                .setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL).setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.registerModule(new MrBeanModule());
        return mapper;
    }
}
