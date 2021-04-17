package ua.tqs.ex3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarManagerService carService = new CarManagerService();

    @PostMapping(path="/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        Car saved = carService.save(car);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @GetMapping(path = "/cars")
    public ResponseEntity<?> getAllCars(){
        List<Car> all = carService.getAllCars();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(path = "cars/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Long id){
        Car c = carService.getCarDetails(id).orElse(null);
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
}
