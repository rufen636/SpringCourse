package com.example.javacourse.models;

import jakarta.persistence.*;


@Entity
@Table(name = "responses")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}


