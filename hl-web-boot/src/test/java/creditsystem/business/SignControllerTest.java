package com.hualongdata.springstarter.business;

import com.hualongdata.springstarter.Application;
import com.hualongdata.springstarter.web.controllers.sign.SignController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by yangbajing on 16-9-8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Commit
@Transactional
public class SignControllerTest {


    // 模拟request,response
    // private MockHttpServletRequest request;
    //private MockHttpServletResponse response;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    SignController signController;

    String tokenCode = null;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        // tokenCode =  Signin.testSignin(mockMvc,"1724430240@qq.com","a123456");
    }

    @Test
    //用户登录
    public void testSignin() throws Exception {  ///sign/signin
        String json = "{\n" +
                "  \"account\": \"1724430240@qq.com\",\n" +
                "  \"captcha\": \"string\",\n" +
                "  \"password\": \"a123456\",\n" +
                "  \"smsCode\": \"string\"\n" +
                "}";
        String re = mockMvc.perform(put("/sign/signin", "json").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes())).andReturn()
                .getResponse().getHeader("cc-o-t");
        System.out.println(json);
        System.out.println(re);
    }

    @Test
    //获得当前用户信息
    public void testGet() throws Exception {
        String responseString = mockMvc.perform(
                get("/inapi/user").cookie(new Cookie("cc-o-t", tokenCode))
                //header("cc-o-t",code)    //请求的url,请求的方法是get
                // .contentType(MediaType.APPLICATION_JSON) //数据的格式
                //.param("pcode","root")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = " + responseString);

    }

    @Test
    //用户注册
    public void testSignup() throws Exception {
        String json = "{\n" +
                "  \"account\": \"a123456@qq.com\",\n" +
                "  \"captcha\": \"得得得\",\n" +
                "  \"password\": \"a123456\",\n" +
                "  \"smsCode\": \"string\"\n" +
                "}";
        System.out.println("--------请求的json = " + json);
        String responseString = mockMvc.perform(post("/sign/signup", "json")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes())).andExpect(status().isCreated())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        System.out.println("--------返回的json = " + responseString);

    }
}
