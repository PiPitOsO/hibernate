package com.example.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Persons {

    @EmbeddedId
    private Contact contact;

    private long phoneNumber;

    @Column(nullable = false)
    private String cityOfLiving;
}
