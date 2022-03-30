package com.example.hibernate.service;

import com.example.hibernate.repository.MyRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {
    private final MyRepository repository;

    public MyService(MyRepository repository) {
        this.repository = repository;
    }

    public List getPersonsByCity(String city) throws EmptyResultDataAccessException {
        return repository.get(city);
    }
}
