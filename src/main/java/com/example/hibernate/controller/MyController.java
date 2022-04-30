package com.example.hibernate.controller;

import com.example.hibernate.service.MyService;
import com.google.gson.Gson;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/persons")
public class MyController {
    private final MyService service;

    public MyController(MyService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    @PreAuthorize("authentication.principal.username == #name")
    public String hello(@RequestParam(required = false, defaultValue = "guest") String name){
        return "Hello ";
    }

    @GetMapping("/by-city")
    @Secured("ROLE_READ")
    public void city(@RequestParam(required = false) String city, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final var data = service.getPersonsByCity(city);
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }

    @GetMapping("/by-age")
    @RolesAllowed("ROLE_WRITE")
    public void age(@RequestParam(required = false) int age, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final var data = service.getPersonsByAge(age);
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }

    @GetMapping("/by-name-surname")
    @PreAuthorize("hasRole('WRITE') or hasRole('DELETE')")
    public void nameSurname(@RequestParam(required = false) String name, String surname, HttpServletResponse response) throws Exception {
        response.setContentType("application/json");
        final var data = service.getPersonsByNameSurname(name, Optional.ofNullable(surname).orElse("Sidorov"));
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }
}