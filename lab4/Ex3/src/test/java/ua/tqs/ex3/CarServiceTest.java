package ua.tqs.ex3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.ex3.Car;
import ua.tqs.ex3.CarRepository;
import ua.tqs.ex3.CarManagerService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.mockito.InjectMocks;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService cService;

    @BeforeEach
    public void setUp(){
        Car merc = new Car("mercedes", "A180");
        // Need to set Id since we would only get the Ids of the Objects if they were stored in a DB,
        merc.setId(1L);
        Car audi = new Car("Audi", "R8");
        audi.setId(2L);
        List<Car> cars = Arrays.asList(merc,audi);
        given(carRepository.findById(merc.getId())).willReturn(Optional.of(merc));
        given(carRepository.findById(audi.getId())).willReturn(Optional.of(audi));
        given(carRepository.findAll()).willReturn(cars);
    }

    @Test
    public void validID_carFound() throws Exception {
        Long id = 1L;
        Car byId = cService.getCarDetails(id).orElse(null);
        assertThat(byId).isNotNull();
        assertThat(byId.getId()).isEqualTo(1L);
        verify(carRepository, VerificationModeFactory.times(1)).findById(Mockito.any());
    }
    @Test
    public void invalidID_carNotFound() throws Exception {
        Car byId = cService.getCarDetails(3L).orElse(null);
        assertThat(byId).isEqualTo(null);
        verify(carRepository, VerificationModeFactory.times(1)).findById(Mockito.any());
    }

    @Test
    public void getAllCars_returnsAllRecords(){
        Car merc = new Car("mercedes","A180");
        Car audi = new Car("Audi", "R8");
        List<Car> results = cService.getAllCars();
        assertThat(results.size()).isEqualTo(2);
        assertThat(results).extracting(Car::getMaker).contains(merc.getMaker());
        assertThat(results).extracting(Car::getMaker).contains(audi.getMaker());
    }
}
