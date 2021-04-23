package ua.tqs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BlazeDemoTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @Given("I navigate to {string}")
  public void setUp(String url) {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    driver.get(url);
  }

  @When("I select and fill form for flight with departure in {string} and destination in {string}")
  public void bookFlight(String departure, String arrival) {

    driver.manage().window().setSize(new Dimension(831, 697));
    driver.findElement(By.name("fromPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = '"+departure+"']")).click();
    }
    driver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(3)")).click();
    driver.findElement(By.name("toPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = '"+arrival+"']")).click();
    }
    driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(7)")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(2) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Manuel");
    driver.findElement(By.id("inputName")).sendKeys(Keys.DOWN);
    driver.findElement(By.id("inputName")).sendKeys("manuel silveira");
    driver.findElement(By.cssSelector(".control-group:nth-child(4) > .control-label")).click();
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("rua dos trevos Nº4");
    driver.findElement(By.id("city")).click();
    driver.findElement(By.id("city")).sendKeys("Aveiro");
    driver.findElement(By.id("state")).click();
    driver.findElement(By.id("state")).sendKeys("California");
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("12345");
    driver.findElement(By.id("cardType")).click();
    driver.findElement(By.cssSelector("option:nth-child(3)")).click();
    driver.findElement(By.id("creditCardNumber")).click();
    driver.findElement(By.id("creditCardNumber")).sendKeys("111111111");
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.id("nameOnCard")).sendKeys("ma");
    driver.findElement(By.id("nameOnCard")).sendKeys(Keys.DOWN);
    driver.findElement(By.id("nameOnCard")).sendKeys("manuel silveira");
    driver.findElement(By.id("rememberMe")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
  }

  @Then("I navigate to {string} where it shows {string}")
  public void resultPage(String url, String message){
    assertThat(driver.getCurrentUrl(),equalTo(url));
    assertThat(driver.findElement(By.tagName("h1")).getText(),equalTo(message));
    driver.close();
  }
}
