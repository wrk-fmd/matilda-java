package at.wrk.fmd.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.web.server.LocalServerPort;

import at.wrk.fmd.environment.Environment;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ReportWebsiteTest extends Environment {
    
    // Handles more or less all elements on Report website

    @Test
    public void isReportCaptionAvailable() {
        login();

        driver.findElement(By.xpath("//a[@href='/report']")).click();
        
        assertTrue(driver.findElement(By.tagName("h2")).getText().equals("Report für informative Ansichten"));
    }
    
    @Test
    public void isReportWebsiteAvailableAndClickable() {
        login();

        driver.findElement(By.xpath("//a[@href='/report']")).click();
        
        assertTrue(driver.getTitle().contains("Report"));
    }
    
    @AfterClass
    public static void tearDown() {
        if(driver!=null)
            driver.quit();
    }
}