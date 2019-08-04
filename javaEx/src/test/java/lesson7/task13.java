package lesson7;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class task13 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void testForTask13(){
        getToMainPage();
        openFirstProduct();
        addToCartAndCheckCartNumber();
        clickReturnHome();
        openSecondProduct();
        addToCartAndCheckCartNumber();
        clickReturnHome();
        openThirdProduct();
        addToCartAndCheckCartNumber();
        clickCheckout();
        removeProductsFromTable();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

    private void getToMainPage(){
        driver.get("http://litecart.stqa.ru/en/");
    }

    private void openFirstProduct() {
        List<WebElement> products = driver.findElements(By.cssSelector("li[class^='product']"));
        WebElement first = products.get(0);
        first.click();
    }

    private void openSecondProduct() {
        List<WebElement> products = driver.findElements(By.cssSelector("li[class^='product']"));
        WebElement second = products.get(1);
        second.click();
    }

    private void openThirdProduct() {
        List<WebElement> products = driver.findElements(By.cssSelector("li[class^='product']"));
        WebElement third = products.get(2);
        third.click();
    }

    private void addToCart(){
        if (driver.findElements(By.xpath(".//select[@name='options[Size]']")).size() > 0){
            driver.findElement(By.xpath(".//select[@name='options[Size]']/option[2]")).click();
            driver.findElement(By.xpath(".//button[@name='add_cart_product']")).click();
            wait = new WebDriverWait(driver, 5);
        }
        else {
            driver.findElement(By.xpath(".//button[@name='add_cart_product']")).click();
            wait = new WebDriverWait(driver, 5);
        }
    }

    private void addToCartAndCheckCartNumber(){
        String cartNumber = driver.findElement(By.xpath(".//span[@class='quantity']")).getText();
        addToCart();
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(".//span[@class='quantity']"),cartNumber));
        Assert.assertFalse(cartNumber.equals(driver.findElement(By.xpath(".//span[@class='quantity']")).getText()));
    }

    private void clickReturnHome(){
        driver.findElement(By.xpath(".//a[text()='Home']"))
                .click();
        wait = new WebDriverWait(driver, 5);
    }

    private void clickCheckout(){
        driver.findElement(By.xpath(".//a[text()='Checkout »']"))
                .click();
        wait = new WebDriverWait(driver, 5);
    }

    private void removeProductsFromTable(){
        List<WebElement> productsInTable = driver.findElements(By.xpath(".//table[@class='dataTable rounded-corners']//tr/td[@class='item']"));
        for (WebElement product: productsInTable) {
            for (int i = 0; i < 10; i ++){
                try{
                    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//button[@name='remove_cart_item']"))));
                    driver.findElement(By.xpath(".//button[@name='remove_cart_item']")).click();
                    break;
                }
                catch (Exception e){
                    System.out.println("Remove was not performed.");
                }
            }
            wait.until(ExpectedConditions.invisibilityOf(product));
        }
    }
}