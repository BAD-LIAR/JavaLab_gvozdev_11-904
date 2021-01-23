package ru.itis.javalab.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Car {
    private Long id;
    private String model;
    private Double weight;
    private User owner;
}
