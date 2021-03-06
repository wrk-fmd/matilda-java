package at.wrk.fmd.matilda.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import at.wrk.fmd.matilda.environment.EnvironmentAbstractSeleniumTest;

public class PasswordAenderungSeleniumTest extends EnvironmentAbstractSeleniumTest {

    @Test
    public void isTitlePasswortAendernAvailable() {
        login();
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Passwort aendern')]")).click();

        assertTrue(driver.getTitle().contains("Passwort ändern"));
    }
    
    @AfterClass
    public static void tearDown() {
        if(driver!=null)
            driver.quit();
    }    
}
