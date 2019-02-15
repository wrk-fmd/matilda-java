package at.wrk.fmd.selenium;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginWebsiteTest {
    @LocalServerPort
    private int port;
    private static WebDriver driver;
    
    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void isCaptionAvailable() {
        driver.get("https://localhost:" + port + "/login");

        String title = driver.getTitle();
        assertTrue(title.contains("Matilda Login"));
    }
    
    @Test
    public void isLOGINHeadingAvailable() {
        driver.get("https://localhost:" + port + "/login");
        
        String loginHeading = driver.findElement(By.tagName("h4")).getText();
        assertTrue(loginHeading.contains("LOGIN"));
    }

    @Test
    public void isUsernameAvailable() {
        driver.get("https://localhost:" + port + "/login");

        List<WebElement> elements = driver.findElements(By.cssSelector("div.form-group input[type = 'text']"));
        for(WebElement el : elements) 
        {
           String username = el.getAttribute("id");
           assertThat(username.equals("username"));
        }
    }
    
    @Test
    public void isPasswordAvailable() {
        driver.get("https://localhost:" + port + "/login");

        List<WebElement> elements = driver.findElements(By.cssSelector("div.form-group input[type = 'text']"));
        for(WebElement el : elements) 
        {
           String password = el.getAttribute("id");
           assertThat(password.equals("password"));
        }
    }
    
    @Test
    public void isButtonTitleAvailable() {
        List<WebElement> elements = driver.findElements(By.cssSelector("div.form-group input[type = 'submit']"));
        for(WebElement el : elements) 
        {
           String buttonTitle = el.getAttribute("id");
           assertThat(buttonTitle.equals("login-submit"));
        }
    }

    @Test
    public void commitButton() {
        driver.get("https://localhost:" + port + "/login");
        
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();

        String title = driver.getTitle();
        assertTrue(title.contains("Matilda Hauptseite"));
    }
    
    @Test
    public void isLagerstandortLinkAvailable() {
        driver.get("https://localhost:" + port + "/login");
        
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();

        String standort = driver.findElement(By.linkText("Lagerstandort")).getText();

        assertTrue(standort.contains("Lagerstandort"));
    }
    
    @Test
    public void isReportLinkAvailable() {
        driver.get("https://localhost:" + port + "/login");
        
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();

        String report = driver.findElement(By.linkText("Report")).getText();

        assertTrue(report.contains("Report"));
    }
    
    @Test
    public void isEinheitentypLinkAvailable() {
        driver.get("https://localhost:" + port + "/login");
        
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();

        WebElement dropdown = driver.findElement(By.className("dropdown-content"));
        List<WebElement> items = dropdown.findElements(By.xpath("//a"));

        List<String> listOfAnchors = new ArrayList();
        boolean isFound = false;
        for(WebElement ele : items) {
            listOfAnchors.add(ele.getAttribute("href"));
        }
        for(String str: listOfAnchors) {
            if(str.trim().contains("einheitentyp"))
                isFound = true;
        }
        assertTrue(isFound);
    }
    
    @Test
    public void isReportWebsiteAvailableAndClickable() {
        driver.get("https://localhost:" + port + "/login");
        
        driver.findElement(By.id("username")).sendKeys("ADMIN");
        driver.findElement(By.id("password")).sendKeys("#WRK#");
        driver.findElement(By.id("login-submit")).click();

        driver.findElement(By.xpath("//a[@href='/report']")).click();
        
        String title = driver.getTitle();
        assertTrue(title.contains("Report"));
    }
    
    @AfterClass
    public static void tearDown() {
        if(driver!=null)
            driver.quit();
    }
}