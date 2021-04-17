package ua.tqs.ex3;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.tqs.ex3.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    public Optional<Car> findById(Long id);
    public List<Car> findAll();
}
