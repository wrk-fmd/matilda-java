package at.wrk.fmd.matilda.environment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class EnvironmentAbstractSeleniumTest {

    @LocalServerPort
    protected int port;
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public WebDriver onlyLogin() {
        driver.get("https://localhost:" + port + "/login");
        return driver;
    }

    public WebDriver login() {
        driver.get("https://localhost:" + port + "/login");

        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();
        return driver;
    }

    public WebDriver wrongLogin() {
        driver.get("https://localhost:" + port + "/login");

        driver.findElement(By.id("username")).sendKeys("123");
        driver.findElement(By.id("password")).sendKeys("#123#");
        driver.findElement(By.id("login-submit")).click();
        return driver;
    }
}