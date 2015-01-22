package com.tangzq;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;


public class TestAPIWithClient {
    Client client;


    @Before
    public void init() {
        client = Client.create();
    }

    @Test
    public void testGet() {
        WebResource webResource = client.resource("http://localhost:8080/rest/user/users/lucy");
        ClientResponse cr = webResource.get(ClientResponse.class);
        try {
            String resString = IOUtils.toString(cr.getEntityInputStream());
            System.out.println(resString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.print(webResource.get(String.class));
    }

    @Test
    public void testGetJson() {
        WebResource webResource = client.resource("http://localhost:8080/rest/user/users/list");
        ClientResponse cr = webResource.get(ClientResponse.class);
        try {
            String resString = IOUtils.toString(cr.getEntityInputStream());
            System.out.println(resString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPost() {
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("id", "100");
        formData.add("username", "小菲菲");
        WebResource webResource = client.resource("http://localhost:8080/rest/user/save");
        ClientResponse response = webResource.type("application/x-www-form-urlencoded").post(ClientResponse.class, formData);
        try {
            String resString = IOUtils.toString(response.getEntityInputStream());
            System.out.println(resString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
