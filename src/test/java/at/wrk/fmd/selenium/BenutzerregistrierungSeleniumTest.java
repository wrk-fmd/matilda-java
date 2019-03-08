package at.wrk.fmd.selenium;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import at.wrk.fmd.environment.EnvironmentAbstractSeleniumTest;

public class BenutzerregistrierungSeleniumTest extends EnvironmentAbstractSeleniumTest {
    
    // Handles more or less all elements on Registration website

    @Test
    public void isCaptionBenutzerregistrierungAvailable() {
        login();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();

        assertTrue(driver.getTitle().contains("Registration"));
    }
    
    @Test
    public void isAnzeigenameAvailable() {
        login();
        
        Actions builder = new Actions(driver);
        
        WebElement element = driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        assertTrue(driver.findElement(By.id("anzeigename")).isDisplayed());
    }

    @Test
    public void isDienstnummerAvailable() {
        login();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        assertTrue(driver.findElement(By.id("dienstnummer")).isDisplayed());
    }
    
    @Test
    public void isBenutzernameAvailable() {
        login();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        assertTrue(driver.findElement(By.id("benutzername")).isDisplayed());
    }
    
    @Test
    public void isKonfirmationnameAvailable() {
        login();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        assertTrue(driver.findElement(By.id("konfBenutzername")).isDisplayed());
    }

    @Test
    public void isPasswordAvailable() {
        login();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        assertTrue(driver.findElement(By.id("passwort")).isDisplayed());
    }
    
    // Rollen radio-group
    
    @Test
    public void isRolleAdminAvailable() {
        login();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        boolean isAdmin = false;
        List<WebElement> rollenRadioBtn = driver.findElements(By.id("rollen"));

        for(WebElement el : rollenRadioBtn) {
            if(el.getAttribute("value").equals("ADMIN")) {
                isAdmin = true;
            }
        }
        assertTrue(isAdmin);        
    }

    @Test
    public void isRolleSupervisorAvailable() {
        login();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        boolean isSupervisor = false;
        List<WebElement> rollenRadioBtn = driver.findElements(By.id("rollen"));

        for(WebElement el : rollenRadioBtn) {
            if(el.getAttribute("value").equals("SUPERVISOR")) {
                isSupervisor = true;
            }
        }
        assertTrue(isSupervisor);        
    }

    @Test
    public void isRolleBenutzerAvailable() {
        login();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        boolean isBenutzer = false;
        List<WebElement> rollenRadioBtn = driver.findElements(By.id("rollen"));

        for(WebElement el : rollenRadioBtn) {
            if(el.getAttribute("value").equals("BENUTZER")) {
                isBenutzer = true;
            }
        }
        assertTrue(isBenutzer);        
    }

    @Test
    public void createNewUser() {
        login();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Benutzerregistrierung')]")).click();
        
        driver.findElement(By.id("anzeigename")).sendKeys("Test");
        driver.findElement(By.id("dienstnummer")).sendKeys("Test");
        driver.findElement(By.id("benutzername")).sendKeys("Test");
        driver.findElement(By.id("konfBenutzername")).sendKeys("Test");
        driver.findElement(By.id("passwort")).sendKeys("TestUser");
        driver.findElement(By.id("rollen")).click();
        driver.findElement(By.id("login-submit")).click();
        
        WebElement el = driver.findElement(By.xpath("//div[@class='alert alert-info']"));
        
        assertTrue(el.getText().equals("Your user was successfully registered"));
    }
    
    @AfterClass
    public static void tearDown() {
        if(driver!=null)
            driver.quit();
    }
}