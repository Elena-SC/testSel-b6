package lesson4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class task7 {
    private WebDriver driver;

    @Before
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void testForTask3(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath(".//div[@class='notice success']")).isDisplayed();
        clickFunction();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

    private void clickFunction() {
        String locatorSpan =  ".//ul[@class='docs']//span[@class='name']";
        List<WebElement> lis = driver.findElements(By.id("app-"));
        for (int i = 1; i <= lis.size(); i++) {
            driver.findElement(By.xpath(".//li[" +i+ "]")).click();
            if (isElementPresent(driver, By.xpath(locatorSpan))) {
                List<WebElement> spans = driver.findElements(By.xpath(locatorSpan));
                for (int j = 1; j <= spans.size(); j++)  {
                   driver.findElement(By.xpath(".//ul[@class='docs']/li["+j+"]/a/span[@class='name']")).click();
                   driver.findElement(By.xpath(".//h1"));
                }
            }
        }
    }
    private boolean isElementPresent(WebDriver driver, By locator){
        return driver.findElements(locator).size() > 0;
    }
}
