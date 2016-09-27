package com.hualongdata.springstarter.business;

import com.hualongdata.springstarter.Application;
import com.hualongdata.springstarter.business.service.RoleService;
import com.hualongdata.springstarter.data.model.Role;
import com.hualongdata.springstarter.web.controllers.sign.SignController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Commit
@Transactional
public class RoleServerTest {


    // 模拟request,response
    // private MockHttpServletRequest request;
    //private MockHttpServletResponse response;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    SignController signController;

    @Autowired
    RoleService rs;

    String tokenCode = null;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        tokenCode = Signin.testSignin(mockMvc, "1724430240@qq.com", "a123456");
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
    //角色创建
    public void testCreate() throws Exception {
//        String json="{\n" +
//                "  \"createdAt\": \"string\",\n" +
//                "  \"description\": \"string\",\n" +
//                "  \"id\": 0,\n" +
//                "  \"name\": \"普通\",\n" +
//                "  \"privilege\": \"aaa,bbb\",\n" +
//                "  \"updatedAt\": \"string\"\n" +
//                "}";
        Role r = new Role();
        r.setName("普通");
        r.setPrivilege("aa,bb");
        rs.crateRole(r);

    }
}
