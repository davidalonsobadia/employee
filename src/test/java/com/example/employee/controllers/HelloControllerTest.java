package com.example.employee.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
public class HelloControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(new HelloController()).build();
    }

    @Test
    public void whenGetSayHello_thenHelloWorldIsReturned() throws Exception {
        // when
        MvcResult result = mockMvc.perform(get("/hello-world"))
                .andReturn();

        // then
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Hello, world!");
    }
}
