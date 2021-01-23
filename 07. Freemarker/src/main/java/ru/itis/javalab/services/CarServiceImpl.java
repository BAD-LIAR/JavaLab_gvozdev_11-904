package ru.itis.javalab.services;

import ru.itis.javalab.models.Car;
import ru.itis.javalab.repositories.CarRepository;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;

public class CarServiceImpl implements CarService{

    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    @Override
    public Car findCar(Integer id) {
        return carRepository.findCar(id);
    }

    @Override
    public void saveCar(String model, Long ownerId) {
        carRepository.saveCar(model, ownerId);
    }
}
