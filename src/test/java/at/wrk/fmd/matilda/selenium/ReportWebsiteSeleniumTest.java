package at.wrk.fmd.matilda.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import at.wrk.fmd.matilda.environment.EnvironmentAbstractSeleniumTest;

public class ReportWebsiteSeleniumTest extends EnvironmentAbstractSeleniumTest {
    
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