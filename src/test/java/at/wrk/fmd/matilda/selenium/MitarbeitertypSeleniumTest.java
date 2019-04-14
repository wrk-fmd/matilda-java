package at.wrk.fmd.matilda.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import at.wrk.fmd.matilda.environment.EnvironmentAbstractSeleniumTest;

public class MitarbeitertypSeleniumTest extends EnvironmentAbstractSeleniumTest {
    @Test
    public void isCaptionAvailable() {
        login();

        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdown']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Mitarbeitertyp')]")).click();
        
        String title = driver.getTitle();
        assertTrue(title.contains("Mitarbeitertyp"));
    }
    
    @Test
    public void isTitleMitarbeitertypAvailable() {
        login();

        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdown']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Mitarbeitertyp')]")).click();
        
        assertTrue(driver.getTitle().contains("Mitarbeitertyp"));
    }
    
    @AfterClass
    public static void tearDown() {
        if(driver!=null)
            driver.quit();
    }
}
