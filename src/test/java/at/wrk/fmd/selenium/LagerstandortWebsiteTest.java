package at.wrk.fmd.selenium;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import at.wrk.fmd.environment.Environment;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LagerstandortWebsiteTest extends Environment {
    
    // Handles more or less all elements on lagerstandort website
    
    @Test
    public void isLagerstandortWebsiteAvailableAndClickable() {
        login();

        driver.findElement(By.xpath("//a[@href='/lagerstandort']")).click();
        
        String title = driver.getTitle();
        assertTrue(title.contains("Lagerstandort"));
    }
    
    @Test
    public void isUsernameOnWebsiteLagerstandortAvailable() {
        login();
        
        driver.findElement(By.xpath("//a[@href='/lagerstandort']")).click();
        
        boolean isUsernameAvailable = false;
        String username = null;
        List<WebElement> elements = driver.findElements(By.cssSelector("div.row input[type = 'text']"));
        for(WebElement el : elements) 
        {
           username = el.getAttribute("name");
           if(username.equals("name"))
               isUsernameAvailable = true;
        }
        assertTrue(isUsernameAvailable);
    }
    
    @Test
    public void isAdresseOnWebsiteLagerstandortAvailable() {
        login();

        driver.findElement(By.xpath("//a[@href='/lagerstandort']")).click();
        
        boolean isAdresseAvailable = false;
        String adresse = null;
        List<String> listOfInputElements = new ArrayList<String>();
        
        List<WebElement> elements = driver.findElements(By.cssSelector("div.row input[type = 'text']"));
        for(WebElement el : elements) 
        {
            adresse = el.getAttribute("name");
            listOfInputElements.add(adresse);
        }

        for(String element : listOfInputElements)
            if(element.equals("adresse"))
                isAdresseAvailable = true;
        assertTrue(isAdresseAvailable);
    }
    
    @Test
    public void isBeschreibungOnWebsiteLagerstandortAvailable() {
        login();

        driver.findElement(By.xpath("//a[@href='/lagerstandort']")).click();
        
        boolean isBeschreibungAvailable = false;
        String beschreibung = null;
        List<String> listOfInputElements = new ArrayList<String>();
        
        List<WebElement> elements = driver.findElements(By.cssSelector("div.row input[type = 'text']"));
        for(WebElement el : elements) 
        {
            beschreibung = el.getAttribute("name");
            listOfInputElements.add(beschreibung);
        }

        for(String element : listOfInputElements)
            if(element.equals("beschreibung"))
                isBeschreibungAvailable = true;
        assertTrue(isBeschreibungAvailable);
    }
    
    @Test
    public void isVerantwortlicherAdminOnWebsiteLagerstandortAvailable() {
        login();

        driver.findElement(By.xpath("//a[@href='/lagerstandort']")).click();
        
        boolean isVerantwortlicherAvailable = false;
        String verantwortlicher = null;
        List<String> listOfInputElements = new ArrayList<String>();
        
        Select dropdown = new Select(driver.findElement(By.name("benutzer")));
        List<WebElement> elements = dropdown.getOptions();

        for(WebElement el : elements) 
        {
            verantwortlicher = el.getText();
            listOfInputElements.add(verantwortlicher);
        }

        for(String element : listOfInputElements)
            if(element.equals("ADMIN"))
                isVerantwortlicherAvailable = true;
        assertTrue(isVerantwortlicherAvailable);
    }

    @AfterClass
    public static void tearDown() {
        if(driver!=null)
            driver.quit();
    }
}