package com.hualongdata.springstarter.business;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by Administrator on 2016/9/19.
 */
public class Signin {


    public static String testSignin(MockMvc mockMvc, String acount, String pass) {  ///sign/signin
        String json = "{\n" +
                "  \"account\": \"" + acount + "\",\n" +
                "  \"captcha\": \"string\",\n" +
                "  \"password\": \"" + pass + "\",\n" +
                "  \"smsCode\": \"string\"\n" +
                "}";
        String re = null;
        try {
            re = mockMvc.perform(post("/sign/signin", "json").characterEncoding("UTF-8")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json.getBytes())).andReturn()
                    .getResponse().getHeader("cc-o-t");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===========请求JSON======");
        System.out.println(json);
        System.out.println("==========请求返回=============");
        System.out.println(re);

        return re;
    }
}
