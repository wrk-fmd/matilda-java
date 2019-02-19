package at.wrk.fmd.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import at.wrk.fmd.environment.Environment;

public class PasswordAenderungTest extends Environment {

    @Test
    public void isTitleMaterialAvailable() {
        login();
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Passwort aendern')]")).click();

        assertTrue(driver.getTitle().contains("Passwortaenderung"));
    }
    
    @AfterClass
    public static void tearDown() {
        if(driver!=null)
            driver.quit();
    }    
}
