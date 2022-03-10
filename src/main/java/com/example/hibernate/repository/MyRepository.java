package com.example.hibernate.repository;

import com.example.hibernate.entity.Contact;
import com.example.hibernate.entity.Persons;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Repository
public class MyRepository implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    public List get(String sity) {
        try {
            Query query = entityManager.createQuery("select p.contact.name from Persons p where p.cityOfLiving = :city", Persons.class);
            query.setParameter("cityOfLiving", sity);
            return query.getResultList();
        } catch (EmptyResultDataAccessException e) {
            System.out.println("не верный запрос");
            return null;
        }
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<String> cities = List.of("Москва", "СПб", "Воронеж", "Псков", "Вологда", "Новосибирск", "Красное Село", "Мурино");
        var names = List.of("Лида", "Влад", "Вова", "Кирилл", "Саша", "Тема", "Даша", "Боря", "Варя", "Дима", "Наташа", "Юра");
        var surnames = List.of("Иванов", "Петров", "Сидоров", "Пупкин", "Хомченков", "Давыдов", "Букреев");

        var random = new Random();
        IntStream.range(0, 100)
                .forEach(i -> {
                    var persons = Persons.builder()
                            .contact(Contact.builder()
                                    .name(names.get(random.nextInt(names.size())))
                                    .surname(surnames.get(random.nextInt(surnames.size())))
                                    .age(random.nextInt(60))
                                    .build())
                            .phoneNumber(ThreadLocalRandom.current().nextLong(8900_000_00_00L, 8999_999_99_99L))
                            .cityOfLiving(cities.get(random.nextInt(cities.size())))
                            .build();

                    entityManager.persist(persons);
                });
    }
}
