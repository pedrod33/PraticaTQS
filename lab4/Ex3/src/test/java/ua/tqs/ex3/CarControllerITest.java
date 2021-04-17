package ua.tqs.ex3;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import ua.tqs.ex3.Car;
import ua.tqs.ex3.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "application-integrationtest.properties")
public class CarControllerITest {

    Car audi;
    Car merc;

    @LocalServerPort
    int randomPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    CarRepository cRepository;


    @BeforeEach
    public void setUp(){
        audi = new Car("audi", "R8");
        merc = new Car("mercedes", "A180");
        audi.setId(1L);
        merc.setId(2L);

    }

    @AfterEach
    public void cleanUp(){
        cRepository.deleteAll();
    }

    @Test
    public void whenGetAllCars_ReturnAllCars() throws Exception {
        ResponseEntity<List<Car>> res = restTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>(){});
    }
    @Test
    public void whenGetCarById_ReturnCar() throws Exception{
        ResponseEntity<Car> res = restTemplate.exchange("/api/cars/"+audi.getId(),HttpMethod.GET, null, new ParameterizedTypeReference<Car>(){});
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getBody().getMaker()).isEqualTo(audi.getMaker());
    }

    @Test
    public void postCar_createsCar() throws Exception{
        Car nissan = new Car("nissan","350Z");

        nissan.setId(3L);
        ResponseEntity<Car> res = restTemplate.postForEntity("/api/cars", nissan, Car.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(res.getBody().getMaker()).isEqualTo("nissan");
        List<Car> findRes = cRepository.findAll();
        assertThat(findRes).extracting(Car::getModel).contains("350Z");
    }


}
