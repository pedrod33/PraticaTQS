package ua.tqs.ex3;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ua.tqs.ex3.Car;
import ua.tqs.ex3.CarRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindCarById_ReturnCar(){
        Car audi = new Car("audi", "R8");
        audi.setId(1L);
        entityManager.persistAndFlush(audi);

        Car res = carRepository.findById(1L).orElse(null);
        assertThat(res).isNotNull();
        assertThat(res.getMaker()).isEqualTo(audi.getMaker());
        assertThat(res.getId()).isEqualTo(audi.getId());
    }

    @Test
    public void whenFindInvalidCarById_ReturnCar(){
        Car res = carRepository.findById(2L).orElse(null);
        assertThat(res).isNull();
    }

    @Test
    public void moreThan1Car_FindAll_ReturnAllCars(){
        Car audi = new Car("audi", "R8");
        Car toyota = new Car("toyota", "supra");
        Arrays.asList(audi,toyota).forEach(c ->{
            entityManager.persistAndFlush(c);
        });
        List<Car> resCars = carRepository.findAll();
        assertThat(resCars).isNotNull();
        assertThat(resCars.size()).isEqualTo(2);
        assertThat(resCars).extracting(Car::getMaker).containsOnly(audi.getMaker(),toyota.getMaker());
    }
}
