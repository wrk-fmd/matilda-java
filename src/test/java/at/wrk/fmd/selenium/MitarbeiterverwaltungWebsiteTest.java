package at.wrk.fmd.selenium;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MitarbeiterverwaltungWebsiteTest {
    
    @LocalServerPort
    private int port;
    private static WebDriver driver;
    
    // Handles more or less all elements on Mitarbeiterverwaltung website

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void isMitarbeiterverwaltungLinkAvailable() {
        driver.get("https://localhost:" + port + "/login");
        
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();

        WebElement dropdown = driver.findElement(By.className("dropdown-content"));
        List<WebElement> items = dropdown.findElements(By.xpath("//a"));

        List<String> listOfAnchors = new ArrayList<String>();
        boolean isFound = false;
        for(WebElement ele : items) {
            listOfAnchors.add(ele.getAttribute("href"));
        }
        for(String str: listOfAnchors) {
            if(str.trim().contains("mitarbeiterverwaltung"))
                isFound = true;
        }
        assertTrue(isFound);
    }
    
    @Test
    public void isCaptionMitarbeiterverwaltungAvailable() {
        driver.get("https://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();

        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Mitarbeiterverwaltung')]")).click();

        assertTrue(driver.getTitle().contains("Mitarbeiterverwaltung"));
    }

    @AfterClass
    public static void tearDown() {
        if(driver!=null)
            driver.quit();
    }
}