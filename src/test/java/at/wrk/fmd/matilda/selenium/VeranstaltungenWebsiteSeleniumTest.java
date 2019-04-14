package at.wrk.fmd.matilda.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import at.wrk.fmd.matilda.environment.EnvironmentAbstractSeleniumTest;

public class VeranstaltungenWebsiteSeleniumTest extends EnvironmentAbstractSeleniumTest {
    
    // Handles more or less all elements on Registration website

    @Test
    public void isVeranstaltungenTitleWebsiteAvailable() {
        login();
        
        driver.findElement(By.xpath("//a[@href='/veranstaltung']")).click();
        
        assertTrue(driver.getTitle().contains("Veranstaltung"));
    }
    
    @Test
    public void isLOGINHeadingAvailable() {
        login();
        
        driver.findElement(By.xpath("//a[@href='/veranstaltung']")).click();
        
        assertTrue(driver.findElement(By.tagName("h2")).getText().equals("Übersicht Veranstaltungen"));
    }
    
    @AfterClass
    public static void tearDown() {
        if(driver!=null)
            driver.quit();
    }
}