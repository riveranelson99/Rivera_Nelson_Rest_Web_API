package com.company.RestAPI.controller;

import com.company.RestAPI.Month;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class MonthController {

    public static List<Month> monthsList = new ArrayList<>(Arrays.asList(
            new Month(1, "January"),
            new Month(2, "February"),
            new Month(3, "March"),
            new Month(4, "April"),
            new Month(5, "May"),
            new Month(6, "June"),
            new Month(7, "July"),
            new Month(8, "August"),
            new Month(9, "September"),
            new Month(10, "October"),
            new Month(11, "November"),
            new Month(12, "December")
    ));

    @GetMapping("/month/{monthNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Month randomMonth(@PathVariable Integer monthNumber) {

        for (Month thisMonth : monthsList) {
            if (thisMonth.getNumber().equals(monthNumber)) {
                return thisMonth;
            }
        }
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @GetMapping("/randomMonth")
    @ResponseStatus(HttpStatus.OK)
    public Month randomMonth() {

        Random rand = new Random();
        int randomInt = rand.nextInt(12);

        return monthsList.get(randomInt);
    }
}
