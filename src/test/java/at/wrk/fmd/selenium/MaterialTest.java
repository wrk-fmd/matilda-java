package at.wrk.fmd.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import at.wrk.fmd.environment.Environment;

public class MaterialTest extends Environment {

    @Test
    public void isCaptionAvailable() {
        login();

        driver.findElement(By.xpath("//a[@href='/material']")).click();
        
        assertTrue(driver.findElement(By.tagName("h2")).getText().equals("Aktuelle Lager"));
    }
    
    @Test
    public void isTitleMaterialAvailable() {
        login();

        driver.findElement(By.xpath("//a[@href='/material']")).click();

        assertTrue(driver.getTitle().contains("Material"));
    }
    
    @AfterClass
    public static void tearDown() {
        if(driver!=null)
            driver.quit();
    }
}
