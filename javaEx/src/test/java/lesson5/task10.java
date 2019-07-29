package lesson5;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;

public class task10 {
    private WebDriver driver;
    private String productName;
    private String regPrice;
    private String salePrice;

    @Before
    public void start(){
        driver = new ChromeDriver();
    } //new FirefoxDriver() -  change the driver if needed
                                                                   //new InternetExplorerDriver()

    @Test
    public void testForTask10(){
        driver.get("http://localhost/litecart/en/");
        getCampaignsListValues();
        checkProductTextValues("#box-campaigns .product");
        driver.findElement(By.cssSelector("#box-campaigns .product")).click();
        checkProductTextValues(".information");
        compareDetailAndListPages();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

    private void getCampaignsListValues(){
        WebElement campaignsProduct = driver.findElement(By.cssSelector("#box-campaigns .product"));
            productName = campaignsProduct.findElement(By.cssSelector(".name")).getText();
            regPrice = campaignsProduct.findElement(By.cssSelector(".regular-price")).getText();
            salePrice = campaignsProduct.findElement(By.cssSelector(".campaign-price")).getText();
    }

    private void checkProductTextValues(String blockLocator){
        checkRegPrice( blockLocator);
        checkSalePrice(blockLocator);
        checkPriceTextSize(blockLocator);
    }

    private void checkPriceTextSize(String blockLocator){
        WebElement campaignsProduct = driver.findElement(By.cssSelector(blockLocator));
        Dimension textSizeRegPrice = campaignsProduct.findElement(By.cssSelector(".regular-price")).getSize();
        Dimension textSizeSalePrice = campaignsProduct.findElement(By.cssSelector(".campaign-price")).getSize();
        Assert.assertTrue(textSizeRegPrice.getHeight()*textSizeRegPrice.getWidth() <
                textSizeSalePrice.getHeight()*textSizeSalePrice.getWidth());
    }

    private void compareDetailAndListPages(){
        Assert.assertTrue(productName.equals(driver
                .findElement(By.cssSelector("#box-product .title")).getText()));
        Assert.assertTrue(regPrice.equals(driver
                .findElement(By.cssSelector(".regular-price")).getText()));
        Assert.assertTrue(salePrice.equals(driver
                .findElement(By.cssSelector(".campaign-price")).getText()));
    }

    private void checkRegPrice(String blockLocator){
        WebElement campaignsProduct = driver.findElement(By.cssSelector(blockLocator));
        String colorOfRegPrice = Color.fromString(
                campaignsProduct
                        .findElement(By.cssSelector(".regular-price"))
                        .getCssValue("color")).asHex();
        checkColorRegPrice(colorOfRegPrice);
        Assert.assertTrue(campaignsProduct.findElement(By.cssSelector(".regular-price")).getTagName().equals("s"));
    }

    private void checkSalePrice(String blockLocator){
        WebElement campaignsProduct = driver.findElement(By.cssSelector(blockLocator));
        String colorOfSalePrice = Color.fromString(
                campaignsProduct
                        .findElement(By.cssSelector(".campaign-price"))
                        .getCssValue("color")).asHex();
        checkColorSalePrice(colorOfSalePrice);
        Assert.assertTrue(campaignsProduct.findElement(By.cssSelector(".campaign-price")).getTagName().equals("strong"));
    }

    private void checkColorRegPrice(String colorValue){
        String subStrColor = colorValue.substring(1);
        String r = subStrColor.substring(0, 2);
        String g = subStrColor.substring(2, 4);
        String b = subStrColor.substring(4, 6);
        Assert.assertTrue(r.equals(g) && g.equals(b));
    }

    private void checkColorSalePrice(String colorValue){
        String subStrColor = colorValue.substring(1);
        String g = subStrColor.substring(2, 4);
        String b = subStrColor.substring(4, 6);
        Assert.assertTrue(g.equals(b));
    }
}