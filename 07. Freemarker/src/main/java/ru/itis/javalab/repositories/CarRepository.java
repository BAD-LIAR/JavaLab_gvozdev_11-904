package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Car;
import ru.itis.javalab.models.User;

import java.util.List;

public interface CarRepository extends CrudRepository<Car>{
    List<Car> getAllCars();
    Car findCar(Integer id);

    void saveCar(String model, Long ownerId);
}
