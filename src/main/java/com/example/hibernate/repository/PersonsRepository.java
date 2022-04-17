package com.example.hibernate.repository;

import com.example.hibernate.entity.Persons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonsRepository extends JpaRepository<Persons, Long> {

    List<Persons> findByCityOfLivingLike(String city);

    List<Persons> findByContactAgeLessThanOrderByContactAge(int age);

    List<Persons> findByContactNameAndContactSurnameLike(String name, String surname);
}
