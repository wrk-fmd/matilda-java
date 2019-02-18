package at.wrk.fmd.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import at.wrk.fmd.environment.Environment;

public class VeranstaltungenWebsiteTest extends Environment {
    
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