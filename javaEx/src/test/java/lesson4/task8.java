package lesson4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class task8 {
    private WebDriver driver;

    @Before
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void testForTask8(){
        driver.get("http://localhost/litecart/en/");
        stickerFunction();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

    private void stickerFunction() {
        List <WebElement> productLists = driver.findElements(By.xpath(".//ul[@class='listing-wrapper products']"));
        for (WebElement productList: productLists) {
            List<WebElement> mostPopularItems = productList
                    .findElements(By.xpath(".//li[@class='product column shadow hover-light']"));
            for (WebElement mostPopularItem: mostPopularItems) {
                Assert.assertTrue(isElementPresent(mostPopularItem
                        .findElements(By.cssSelector("div[class^='sticker']"))));
            }
        }
    }
    private boolean isElementPresent(List <WebElement> element) {
        return element.size() == 1;
    }
}
