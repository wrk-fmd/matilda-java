package at.wrk.fmd.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import at.wrk.fmd.environment.Environment;

public class EinheitentypTest extends Environment {

    @Test
    public void isCaptionAvailable() {
        login();

        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdown']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Einheitentyp')]")).click();
        
        String title = driver.getTitle();
        assertTrue(title.contains("Einheitentyp"));
    }
    
    @Test
    public void isTitleEinheitentypAvailable() {
        login();

        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdown']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Einheitentyp')]")).click();
        
        assertTrue(driver.getTitle().contains("Einheitentyp"));
    }
    
    @AfterClass
    public static void tearDown() {
        if(driver!=null)
            driver.quit();
    }
}
