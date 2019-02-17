package at.wrk.fmd.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VeranstaltungenWebsiteTest {
    @LocalServerPort
    private int port;
    private static WebDriver driver;
    
    // Handles more or less all elements on Registration website

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void isVeranstaltungenTitleWebsiteAvailable() {
        driver.get("https://localhost:" + port + "/login");
        
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();

        driver.findElement(By.xpath("//a[@href='/veranstaltung']")).click();
        
        assertTrue(driver.getTitle().contains("Veranstaltung"));
    }
    
    @Test
    public void isLOGINHeadingAvailable() {
        driver.get("https://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();

        driver.findElement(By.xpath("//a[@href='/veranstaltung']")).click();
        
        assertTrue(driver.findElement(By.tagName("h2")).getText().equals("Übersicht Veranstaltungen"));
    }
    
    @AfterClass
    public static void tearDown() {
        if(driver!=null)
            driver.quit();
    }
}