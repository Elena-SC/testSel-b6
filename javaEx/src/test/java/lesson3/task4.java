package lesson3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class task4 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        //driver = new FirefoxDriver();
        driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testForTask3(){
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}

