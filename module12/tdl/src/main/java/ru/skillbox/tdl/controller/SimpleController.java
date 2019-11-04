package ru.skillbox.tdl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Random;

@RestController
@RequestMapping("/")
public class SimpleController {

    @GetMapping
    public String getTime(){
        return LocalDate.now() + " " + new Random().nextInt();
    }
}
