package com.example.employee.controllers;

import com.example.employee.model.Employee;
import com.example.employee.repositories.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void whenGetEmployees_thenEmployeesAreReturned() throws Exception {

        // given
        Employee employee = givenEmployee();
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(employee)));

        // when
        MvcResult result = mvc.perform(get("/employees"))
                .andReturn();

        // then
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.getResponse().getContentAsString()).isNotEmpty();
    }

    @Test
    public void whenPostEmployees_thenEmployeeIsCreated() throws Exception {
        // given
        Employee employee = givenEmployee();
        when(employeeRepository.save(employee)).thenReturn(employee);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestBodyJson = ow.writeValueAsString(employee);

        // when
        MvcResult result = mvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
                .andReturn();

        // then
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(result.getResponse().getContentAsString()).isNotEmpty();
    }

    private Employee givenEmployee() {
        return Employee.builder()
                .name("David")
                .build();
    }
}
