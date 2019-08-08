package lesson10;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;

import java.util.ArrayList;
import java.util.List;

public class Task17 {

    private WebDriver driver;

    @Before
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void testForTask17(){
        loginAndGetToProducts();
        getListOfProducts();
        checkLogsForOpenedProducts(getListOfProducts());
    }

    private void loginAndGetToProducts(){
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    private List<String> getListOfProducts(){
        List<WebElement> products = driver.findElements(By.xpath(".//table[@class='dataTable']//tr[@class='row']/td[3]/a[contains(@href, 'product_id=')]"));
        List<String> listOfProducts = new ArrayList<>();
        for(WebElement product: products) {
            listOfProducts.add(product.getText());
        }
        return listOfProducts;
    }

    private void checkLogsForOpenedProducts(List<String> listOfProducts){
        for (String listOfProduct: listOfProducts) {
            driver.findElement(By.xpath(".//table[@class='dataTable']//tr[@class='row']/td[3]/a[text()='"+ listOfProduct +"']")).click();
            for (LogEntry log: driver.manage().logs().get("browser").getAll()) {
                System.out.println(log);
            }
            driver.findElement(By.xpath(".//button[@name='cancel']")).click();
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}

