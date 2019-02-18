package at.wrk.fmd.selenium;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import at.wrk.fmd.environment.Environment;

public class LoginWebsiteTest extends Environment{
    
    @Test
    public void isCaptionAvailable() {
        onlyLogin();

        String title = driver.getTitle();
        assertTrue(title.contains("Matilda Login"));
    }
    
    @Test
    public void isLOGINHeadingAvailable() {
        onlyLogin();

        String loginHeading = driver.findElement(By.tagName("h4")).getText();
        assertTrue(loginHeading.contains("LOGIN"));
    }

    @Test
    public void isUsernameAvailable() {
        onlyLogin();

        List<WebElement> elements = driver.findElements(By.cssSelector("div.form-group input[type = 'text']"));
        for(WebElement el : elements) 
        {
           String username = el.getAttribute("id");
           assertThat(username.equals("username"));
        }
    }
    
    @Test
    public void isPasswordAvailable() {
        onlyLogin();

        List<WebElement> elements = driver.findElements(By.cssSelector("div.form-group input[type = 'text']"));
        for(WebElement el : elements) 
        {
           String password = el.getAttribute("id");
           assertThat(password.equals("password"));
        }
    }
    
    @Test
    public void isButtonTitleAvailable() {
        List<WebElement> elements = driver.findElements(By.cssSelector("div.form-group input[type = 'submit']"));
        for(WebElement el : elements) 
        {
           String buttonTitle = el.getAttribute("id");
           assertThat(buttonTitle.equals("login-submit"));
        }
    }

    @Test
    public void commitButton() {
        login();

        String title = driver.getTitle();
        assertTrue(title.contains("Matilda Hauptseite"));
    }
    
    @Test
    public void isLagerstandortLinkAvailable() {
        login();

        String standort = driver.findElement(By.linkText("Lagerstandort")).getText();

        assertTrue(standort.contains("Lagerstandort"));
    }
    
    @Test
    public void isReportLinkAvailable() {
        login();

        String report = driver.findElement(By.linkText("Report")).getText();

        assertTrue(report.contains("Report"));
    }
    
    @Test
    public void isEinheitentypLinkAvailable() {
        login();

        WebElement dropdown = driver.findElement(By.className("dropdown-content"));
        List<WebElement> items = dropdown.findElements(By.xpath("//a"));

        List<String> listOfAnchors = new ArrayList<String>();
        boolean isFound = false;
        for(WebElement ele : items) {
            listOfAnchors.add(ele.getAttribute("href"));
        }
        for(String str: listOfAnchors) {
            if(str.trim().contains("einheitentyp"))
                isFound = true;
        }
        assertTrue(isFound);
    }
    
    @Test
    public void isReportWebsiteAvailableAndClickable() {
        login();

        driver.findElement(By.xpath("//a[@href='/report']")).click();
        
        String title = driver.getTitle();
        assertTrue(title.contains("Report"));
    }
    
    @Test
    public void isLogOutPossible() {
        login();

        Actions builder = new Actions(driver);
        
        WebElement element=driver.findElement(By.xpath("//div[@class='dropdownAdmin']"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//div[@class='dropdown-content-admin']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Sign out')]")).click();

        WebElement el = driver.findElement(By.xpath("//div[@class='alert alert-info']"));
        
        assertTrue(el.getText().equals("Sie wurden abgemeldet."));        
    }
}