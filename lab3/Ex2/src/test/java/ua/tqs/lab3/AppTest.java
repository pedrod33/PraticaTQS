package ua.tqs.lab3;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

class SauceLabsHomePageTest {
    WebDriver browser;
    @org.junit.jupiter.api.BeforeEach
    public void init(){
        browser = new FirefoxDriver();
    }

    @org.junit.jupiter.api.AfterEach
    public void teardown(){
        browser.close(); 
    }
    @Test
    public void site_header_is_on_home_page() {
                System.setProperty("webdriver.chrome.driver", "/home/pedro/Downloads/geckodriver-v0.29.0-linux64");  
                browser.get("https://www.saucelabs.com"); 
        WebElement href = browser.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));        
        assertTrue((href.isDisplayed()));
    }
}
