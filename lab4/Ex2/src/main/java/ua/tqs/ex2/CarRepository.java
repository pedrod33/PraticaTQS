package ua.tqs.ex2;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.tqs.ex2.Car;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
}
