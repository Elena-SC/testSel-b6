package lesson8;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class task14 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testForTask14(){
        login();
        clickAddNewCountry();
        openExternalLinks();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

    private void login(){
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    private void clickAddNewCountry() {
        driver.findElement(By.xpath(".//a[text()=' Add New Country']")).click();
    }

    private void openExternalLinks(){
        List<WebElement> externalLinks = driver.findElements(By.cssSelector("i[class='fa fa-external-link']"));
        String mainWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        for(WebElement externalLink: externalLinks) {
            externalLink.click();
            String newWindow = wait.until(anyWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

    private ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows){
        //create new anonymous class to instanciate a method from interface
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver driver) {
                Set<String> handlers = driver.getWindowHandles();
                handlers.removeAll(oldWindows);
                if (handlers.size() > 0) {
                    return handlers.iterator().next();
                }
                return null;
            }
        };
    }
}