package com.example.hibernate.controller;

import com.example.hibernate.service.MyService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/persons")
public class MyController {
    private final MyService service;

    public MyController(MyService service) {
        this.service = service;
    }

    @GetMapping("/by-city")
    public void city(@RequestParam(required = false) String city, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final var data = service.getPersonsByCity(city);
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }

    @GetMapping("/by-age")
    public void age(@RequestParam(required = false) int age, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final var data = service.getPersonsByAge(age);
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }

    @GetMapping("/by-name-surname")
    public void nameSurname(@RequestParam(required = false) String name, String surname, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final var data = service.getPersonsByNameSurname(name, surname);
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }
}