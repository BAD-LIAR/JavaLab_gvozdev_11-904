package ru.itis.javalab.services;

import ru.itis.javalab.models.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car findCar(Integer id);

    void saveCar(String model, Long ownerId);
}
