package at.wrk.fmd.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import at.wrk.fmd.environment.Environment;

public class MitarbeiterverwaltungWebsiteTest extends Environment {
    
    // Handles more or less all elements on Mitarbeiterverwaltung website

    @Test
    public void isMitarbeiterverwaltungLinkAvailable() {
        login();
        WebElement dropdown = driver.findElement(By.className("dropdown-content"));
        List<WebElement> items = dropdown.findElements(By.xpath("//a"));

        List<String> listOfAnchors = new ArrayList<String>();
        boolean isFound = false;
        for(WebElement ele : items) {
            listOfAnchors.add(ele.getAttribute("href"));
        }
        for(String str: listOfAnchors) {
            if(str.trim().contains("mitarbeiterverwaltung"))
                isFound = true;
        }
        assertTrue(isFound);
    }
    
    // Entry point is always our login page, and start page
    // where a mouse click will be performed
    @Test
    public void isCaptionMitarbeiterverwaltungAvailable() {
        login();

        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Mitarbeiterverwaltung')]")).click();

        assertTrue(driver.getTitle().contains("Mitarbeiterverwaltung"));
    }
    
    @Test
    public void isCheckboxTickedInMitarbeiterverwaltung() {
        login();
        
        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Mitarbeiterverwaltung')]")).click();

        boolean isCheckboxTicked = driver.findElement(By.cssSelector("div.row input[type = 'checkBox']")).isSelected();
        
        assertTrue(isCheckboxTicked);
    }

    @Test
    public void checkUncheckTickInMitarbeiterverwaltungAndSave() {
        login();
        
        Actions builder = new Actions(driver);
        
        WebElement element = driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Mitarbeiterverwaltung')]")).click();

        // First check whether a tickbox is set to true and then untick it
        boolean isCheckboxTicked = driver.findElement(By.cssSelector("div.row input[type = 'checkBox']")).isSelected();
        if(isCheckboxTicked) {
            driver.findElement(By.cssSelector("div.row input[type = 'checkBox']")).click();
            isCheckboxTicked = driver.findElement(By.cssSelector("div.row input[type = 'checkBox']")).isSelected();
        }

        // Then click save button
        driver.findElement(By.id("submitButton")).click();

        // After having clicked save button, check whether it was saved
        isCheckboxTicked = driver.findElement(By.cssSelector("div.row input[type = 'checkBox']")).isSelected();
        assertFalse(isCheckboxTicked);
    }
    
    @AfterClass
    public static void tearDown() {
        if(driver!=null)
            driver.quit();
    }
}