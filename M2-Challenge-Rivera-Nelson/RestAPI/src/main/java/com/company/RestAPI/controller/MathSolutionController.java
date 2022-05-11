package com.company.RestAPI.controller;

import com.company.RestAPI.MathSolution;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MathSolutionController {

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution addition(@RequestBody MathSolution add) {

        if (add.getOperand1() == null || add.getOperand2() == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            add.setOperation("add");
            add.setAnswer(add.addition(add.getOperand1(), add.getOperand2()));
            return add;
        }
    }

    @PostMapping("/subtract")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution subtraction(@RequestBody MathSolution subtract) {

        if (subtract.getOperand1() == null || subtract.getOperand2() == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            subtract.setOperation("subtract");
            subtract.setAnswer(subtract.subtraction(subtract.getOperand1(), subtract.getOperand2()));
            return subtract;
        }
    }

    @PostMapping("/multiply")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution multiplication(@RequestBody MathSolution multiply) {

        if (multiply.getOperand1() == null || multiply.getOperand2() == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            multiply.setOperation("multiply");
            multiply.setAnswer(multiply.multiplication(multiply.getOperand1(), multiply.getOperand2()));
            return multiply;
        }
    }

    @PostMapping("/divide")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution division(@RequestBody MathSolution divide) {

        if (divide.getOperand1() == null || divide.getOperand2() == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (divide.getOperand2() == 0) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            divide.setOperation("divide");
            divide.setAnswer(divide.division(divide.getOperand1(), divide.getOperand2()));
            return divide;
        }
    }
}
