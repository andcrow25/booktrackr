package com.ryanpmartz.booktrackr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryanpmartz.booktrackr.BooktrackrApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BooktrackrApplication.class)
@WebAppConfiguration
@Sql("/integration-test-data.sql")
public class UserControllerIntTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testCreatingUser() throws Exception {
        Map<String, String> requestJsonData = new HashMap<>();
        requestJsonData.put("firstName", "New");
        requestJsonData.put("lastName", "User");
        requestJsonData.put("password", "he#4!09ql)C");
        requestJsonData.put("confirmPassword", "he#4!09ql)C");
        requestJsonData.put("email", "newuser@ryanpmartz.com");

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(requestJsonData)))
                .andExpect(status().isCreated());
    }

}
