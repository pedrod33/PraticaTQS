package ua.tqs.ex2;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@SpringBootTest
class Ex2ApplicationTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Container
    public static PostgreSQLContainer pgdb = new PostgreSQLContainer<>()
    .withUsername("user")
    .withPassword("password")
    .withDatabaseName("test");
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", pgdb::getJdbcUrl);
        registry.add("spring.datasource.password", pgdb::getPassword);
        registry.add("spring.datasource.username", pgdb::getUsername);
    }

    @Test
    @Order(1)
    void addInformation() {
        Employee emp1 = new Employee();
        emp1.setName("Ramalho Eanes");
        employeeRepository.save(emp1);
    }

    @Test
    @Order(2)
    void readAllInformation(){
        assertThat(employeeRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    @Order(3)
    void readInformation(){
        Optional<Employee> emp = Optional.ofNullable(employeeRepository.findById(1L).orElse(null));
        assertThat(emp.get().getName()).isEqualTo("Ramalho Eanes");
    }
}
