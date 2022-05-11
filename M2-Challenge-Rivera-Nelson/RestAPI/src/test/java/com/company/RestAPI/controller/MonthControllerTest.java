package com.company.RestAPI.controller;

import com.company.RestAPI.Month;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MonthController.class)
public class MonthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    // Month Converter Success Test
    @Test
    public void shouldReturnMonthBasedOnNumber() throws Exception {
        Month outputMonth = new Month();
        outputMonth.setNumber(11);
        outputMonth.setName("November");

        String outputJson = mapper.writeValueAsString(outputMonth);

        mockMvc.perform(get("/month/11"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // Out of Range Month Converter Test
    @Test
    public void shouldReturn422IfMonthNumberIsOutOfRange() throws Exception {

        mockMvc.perform(get("/month/13"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    // Random Month Success Test
    @Test
    public void shouldReturnARandomMonth() throws Exception {

        mockMvc.perform(get("/randomMonth"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}