package ru.itis.javalab.models;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class Car {
    private Long id;
    private String model;
    private Long ownerId;
}
