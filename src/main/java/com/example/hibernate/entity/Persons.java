package com.example.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Persons {

    @Id
    private long phoneNumber;

    @Embedded
    private Contact contact;

    @Column(nullable = false)
    private String cityOfLiving;
}
