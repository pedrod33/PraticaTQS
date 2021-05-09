package ua.tqs;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class ApiTest {

    @Test
    public void testAPIAll(){
        given().when().get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200);
    }
    @Test
    public void testAPIFour(){
        given()
        .when()
        .get("https://jsonplaceholder.typicode.com/todos/4")
        .then().statusCode(200)
        .body("title", equalTo("et porro tempora"));
    }

    @Test
    public void testAPIIfHasElements(){
        given()
        .when()
        .get("https://jsonplaceholder.typicode.com/todos")
        .then().statusCode(200)
        .body("id", hasItems(198,199));
    }
}
