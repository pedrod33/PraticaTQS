package ua.tqs.ex2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CarController.class)
public class CarControllerTest {

    Car audi;
    Car merc;
    @Autowired
    MockMvc mvc;

    @MockBean
    CarManagerService carService;

    @BeforeEach
    public void setUp(){
        audi = new Car("audi", "R8");
        merc = new Car("mercedes", "A180");
        audi.setId(1L);
        merc.setId(2L);

    }
    @Test
    public void whenGetAllCars_ReturnAllCars() throws Exception {
        when(carService.getAllCars()).thenReturn(Arrays.asList(audi, merc));
        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].maker", is("audi")))
                .andExpect(jsonPath("$[1].maker", is("mercedes")));
    }
    @Test
    public void whenGetCarById_ReturnCar() throws Exception{
        when(carService.getCarDetails(1L)).thenReturn(Optional.of(new Car("audi","R8")));
        mvc.perform(get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.maker", is("audi")));
    }

    @Test
    public void postCar_createsCar() throws Exception{
        Car opel = new Car("opel", "astra");
        when(carService.save(Mockito.any())).thenReturn(opel);
        mvc.perform(post("/api/cars")
                .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(opel)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("opel")));

        verify(carService, times(1)).save(Mockito.any());
    }
}
