package com.company.RestAPI.controller;

import com.company.RestAPI.MathSolution;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MathSolutionController.class)
public class MathSolutionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    // Inputs
    private MathSolution inputAdd;
    private MathSolution inputSubtract;
    private MathSolution inputMultiply;
    private MathSolution inputDivide;

    // Outputs
    private MathSolution outputAdd;
    private MathSolution outputSubtract;
    private MathSolution outputMultiply;
    private MathSolution outputDivide;

    @Before
    public void setUp() {
        // Inputs
        inputAdd = new MathSolution();
        inputAdd.setOperand1(2);
        inputAdd.setOperand2(5);

        inputSubtract = new MathSolution();
        inputSubtract.setOperand1(2);
        inputSubtract.setOperand2(5);

        inputMultiply = new MathSolution();
        inputMultiply.setOperand1(2);
        inputMultiply.setOperand2(5);

        inputDivide = new MathSolution();
        inputDivide.setOperand1(10);
        inputDivide.setOperand2(5);

        // Outputs
        outputAdd = new MathSolution();
        outputAdd.setOperand1(2);
        outputAdd.setOperand2(5);
        outputAdd.setOperation("add");
        outputAdd.setAnswer(7);

        outputSubtract = new MathSolution();
        outputSubtract.setOperand1(2);
        outputSubtract.setOperand2(5);
        outputSubtract.setOperation("subtract");
        outputSubtract.setAnswer(-3);

        outputMultiply = new MathSolution();
        outputMultiply.setOperand1(2);
        outputMultiply.setOperand2(5);
        outputMultiply.setOperation("multiply");
        outputMultiply.setAnswer(10);

        outputDivide = new MathSolution();
        outputDivide.setOperand1(10);
        outputDivide.setOperand2(5);
        outputDivide.setOperation("divide");
        outputDivide.setAnswer(2);
    }

    // Math Operation Tests
    @Test
    public void shouldReturnTwoNumbersAdded() throws Exception {

        String inputJson = mapper.writeValueAsString(inputAdd);
        String outputJson = mapper.writeValueAsString(outputAdd);

        mockMvc.perform(post("/add")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnTwoNumbersSubtracted() throws Exception {

        String inputJson = mapper.writeValueAsString(inputSubtract);
        String outputJson = mapper.writeValueAsString(outputSubtract);

        mockMvc.perform(post("/subtract")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnTwoNumbersMultiplied() throws Exception {

        String inputJson = mapper.writeValueAsString(inputMultiply);
        String outputJson = mapper.writeValueAsString(outputMultiply);

        mockMvc.perform(post("/multiply")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnTwoNumbersDivided() throws Exception {

        String inputJson = mapper.writeValueAsString(inputDivide);
        String outputJson = mapper.writeValueAsString(outputDivide);

        mockMvc.perform(post("/divide")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    // Invalid Input Tests
    @Test
    public void shouldReturn422ForInvalidAdditionRequest() throws Exception {

        MathSolution inputMath = new MathSolution();
        inputMath.setOperand1(5);

        String inputJson = mapper.writeValueAsString(inputMath);

        mockMvc.perform(post("/add")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422ForInvalidSubtractionRequest() throws Exception {

        MathSolution inputMath = new MathSolution();
        inputMath.setOperand1(5);

        String inputJson = mapper.writeValueAsString(inputMath);

        mockMvc.perform(post("/subtract")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422ForInvalidMultiplicationRequest() throws Exception {

        MathSolution inputMath = new MathSolution();
        inputMath.setOperand1(5);

        String inputJson = mapper.writeValueAsString(inputMath);

        mockMvc.perform(post("/multiply")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422ForInvalidDivisionRequest() throws Exception {

        MathSolution inputMath = new MathSolution();
        inputMath.setOperand1(5);

        String inputJson = mapper.writeValueAsString(inputMath);

        mockMvc.perform(post("/divide")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    // Divide By Zero Test
    @Test
    public void shouldReturn422ForDivideByZeroRequest() throws Exception {

        MathSolution inputMath = new MathSolution();
        inputMath.setOperand1(5);
        inputMath.setOperand2(0);

        String inputJson = mapper.writeValueAsString(inputMath);

        mockMvc.perform(post("/divide")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}