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

    public List get(String city) {
            Query query = entityManager.createQuery("select p from Persons p where p.cityOfLiving = :city", Persons.class);
            query.setParameter("city", city);
            return query.getResultList();
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<String> cities = List.of("Moscow", "SPb", "Voronez", "Pscov", "Vologda", "Novosib", "Krasnoe Selo", "Murino");
        var names = List.of("Lida", "Vlad", "Vova", "Kirill", "Saha", "Tema", "Dasha", "Boria", "Varia", "Dima", "Natasha", "Ira");
        var surnames = List.of("Ivanov", "Petrov", "Sidorov", "Pypkin", "Khomchenkov", "Davidov", "Bykreev");

        var random = new Random();
        long[] numbers = ThreadLocalRandom.current().longs(8900_000_00_00L, 8999_999_99_99L).distinct().limit(100).toArray();

        IntStream.range(0, 100)
                .forEach(i -> {
                    var persons = Persons.builder()
                            .phoneNumber(numbers[i])
                            .contact(Contact.builder()
                                    .name(names.get(random.nextInt(names.size())))
                                    .surname(surnames.get(random.nextInt(surnames.size())))
                                    .age(random.nextInt(60))
                                    .build())
                            .cityOfLiving(cities.get(random.nextInt(cities.size())))
                            .build();

                    entityManager.persist(persons);
                });
    }
}
