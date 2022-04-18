package com.example.hibernate.service;

import com.example.hibernate.entity.Persons;
import com.example.hibernate.repository.MyRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyService {
    private final MyRepository repository;

    public MyService(MyRepository repository) {
        this.repository = repository;
    }

    public List getPersonsByCity(String city) throws EmptyResultDataAccessException {
        return repository.getCity(city);
    }

    public List getPersonsByAge(int age) throws EmptyResultDataAccessException {
        return repository.getAge(age);
    }

    public List getPersonsByNameSurname(String name, String surname) throws EmptyResultDataAccessException {
        return repository.getNameSurname(name, surname);
    }
}
