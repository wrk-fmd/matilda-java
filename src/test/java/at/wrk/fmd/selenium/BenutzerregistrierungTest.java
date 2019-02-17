package at.wrk.fmd.selenium;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BenutzerregistrierungTest {
    
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
    public void isCaptionBenutzerregistrierungAvailable() {
        driver.get("https://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();

        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();

        assertTrue(driver.getTitle().contains("Registration"));
    }
    
    @Test
    public void isAnzeigenameAvailable() {
        driver.get("https://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();
        
        Actions builder = new Actions(driver);
        
        WebElement element = driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        assertTrue(driver.findElement(By.id("anzeigename")).isDisplayed());
    }

    @Test
    public void isDienstnummerAvailable() {
        driver.get("https://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        assertTrue(driver.findElement(By.id("dienstnummer")).isDisplayed());
    }
    
    @Test
    public void isBenutzernameAvailable() {
        driver.get("https://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        assertTrue(driver.findElement(By.id("benutzername")).isDisplayed());
    }
    
    @Test
    public void isKonfirmationnameAvailable() {
        driver.get("https://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        assertTrue(driver.findElement(By.id("konfBenutzername")).isDisplayed());
    }

    @Test
    public void isPasswordAvailable() {
        driver.get("https://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        assertTrue(driver.findElement(By.id("passwort")).isDisplayed());
    }
    
    // Rollen radio-group
    
    @Test
    public void isRolleAdminAvailable() {
        driver.get("https://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        boolean isAdmin = false;
        List<WebElement> rollenRadioBtn = driver.findElements(By.id("rollen"));

        for(WebElement el : rollenRadioBtn) {
            if(el.getAttribute("value").equals("ADMIN")) {
                isAdmin = true;
            }
        }
        assertTrue(isAdmin);        
    }

    @Test
    public void isRolleSupervisorAvailable() {
        driver.get("https://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        boolean isSupervisor = false;
        List<WebElement> rollenRadioBtn = driver.findElements(By.id("rollen"));

        for(WebElement el : rollenRadioBtn) {
            if(el.getAttribute("value").equals("SUPERVISOR")) {
                isSupervisor = true;
            }
        }
        assertTrue(isSupervisor);        
    }

    @Test
    public void isRolleBenutzerAvailable() {
        driver.get("https://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        boolean isBenutzer = false;
        List<WebElement> rollenRadioBtn = driver.findElements(By.id("rollen"));

        for(WebElement el : rollenRadioBtn) {
            if(el.getAttribute("value").equals("BENUTZER")) {
                isBenutzer = true;
            }
        }
        assertTrue(isBenutzer);        
    }

    @Test
    public void createNewUser() {
        driver.get("https://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        driver.findElement(By.id("anzeigename")).sendKeys("Test");
        driver.findElement(By.id("dienstnummer")).sendKeys("Test");
        driver.findElement(By.id("benutzername")).sendKeys("Test");
        driver.findElement(By.id("konfBenutzername")).sendKeys("Test");
        driver.findElement(By.id("passwort")).sendKeys("TestUser");
        driver.findElement(By.id("rollen")).click();
        driver.findElement(By.id("login-submit")).click();
        
        WebElement el = driver.findElement(By.xpath("//div[@class='alert alert-info']"));
        
        assertTrue(el.getText().equals("Your user was successfully registered"));
    }
    
    @AfterClass
    public static void tearDown() {
        if(driver!=null)
            driver.quit();
    }
}