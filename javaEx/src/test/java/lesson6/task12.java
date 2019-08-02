package lesson6;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.Random;

public class task12 {
    private WebDriver driver;
    private Random random = new Random();
    private WebDriverWait wait;
    private String productName;

    @Before
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void testForTask12(){
        getToCatalogPage();
        clickAddNewProduct();
        generalTabValues();
        goToInformationTab();
        informationTabValues();
        goToDataTab();
        dataTabValues();
        clickSaveProduct();
        verifyProductIsInTable();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

    private void getToCatalogPage(){
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    private void clickAddNewProduct() {
        driver.findElement(By.xpath(".//a[text()=' Add New Product']"))
                .click();
    }

    private void goToInformationTab(){
        driver.findElement(By.xpath(".//ul[@class='index']//a[text()='Information']"))
                .click();
        wait = new WebDriverWait(driver, 10);
    }

    private void goToDataTab(){
        driver.findElement(By.xpath(".//ul[@class='index']//a[text()='Data']"))
                .click();
        wait = new WebDriverWait(driver, 10);
    }

    private void clickSaveProduct(){
        driver.findElement(By.xpath(".//button[@name='save']"))
                .click();
        wait = new WebDriverWait(driver, 10);
    }

    private void verifyProductIsInTable(){
        int count = 0;
        List<WebElement> products = driver.findElements(By.xpath(".//table[@class='dataTable']//tr[@class='row']/td[3]/a"));
        for (WebElement product: products) {
            if (product.getText().equals(productName)){
                count++;
            }
        }
        Assert.assertTrue(count==1);
    }

    private void generalTabValues() {
        WebElement generalTab = driver.findElement(By.cssSelector(".content #tab-general"));
        generalTab.findElement(By.xpath(".//label[text()=' Enabled']")).click();
        WebElement productNameValue = generalTab.findElement(By.xpath(".//input[@name='name[en]']"));
        productName = "newItem " + random.nextInt(10000);
        productNameValue.sendKeys(productName);
        generalTab.findElement(By.xpath(".//input[@name='code']"))
                .sendKeys("A" + random.nextInt(10000));
        generalTab.findElement(By.xpath(".//input[@data-name='Root']")).click();
        generalTab.findElement(By.xpath(".//input[@data-name='Rubber Ducks']")).click();
        generalTab.findElement(By.xpath(".//input[@name='product_groups[]'][@value='1-3']")).click();
        generalTab.findElement(By.xpath(".//input[@name='quantity']")).clear();
        generalTab.findElement(By.xpath(".//input[@name='quantity']")).sendKeys("1");
        File file = new File("src\\test\\java\\lesson6\\111222.jpg");
        String pathToFile = file.getAbsolutePath();
        generalTab.findElement(By.xpath(".//input[@name='new_images[]']")).sendKeys(pathToFile);
        generalTab.findElement(By.xpath(".//input[@name='date_valid_from']")).sendKeys("12.12.2018");
        generalTab.findElement(By.xpath(".//input[@name='date_valid_to']")).sendKeys("12.12.2019");
    }

    private void informationTabValues() {
        WebElement informationTab = driver.findElement(By.cssSelector(".content #tab-information"));
        informationTab.findElement(By.xpath(".//select[@name='manufacturer_id']")).click();
        informationTab.findElement(By.xpath(".//select[@name='manufacturer_id']/option[2]")).click();
        informationTab.findElement(By.xpath(".//input[@name='keywords']"))
                .sendKeys("keyWords");
        informationTab.findElement(By.xpath(".//input[@name='short_description[en]']"))
                .sendKeys("shortDescription");
        informationTab.findElement(By.xpath(".//div[@class='trumbowyg-editor']")).sendKeys("Description");
        informationTab.findElement(By.xpath(".//input[@name='head_title[en]']")).sendKeys("headTitle");
        informationTab.findElement(By.xpath(".//input[@name='meta_description[en]']")).sendKeys("metaData");
    }

    private void dataTabValues() {
        WebElement informationTab = driver.findElement(By.cssSelector(".content #tab-data"));
        informationTab.findElement(By.xpath(".//input[@name='sku']"))
                .sendKeys("sku");
        informationTab.findElement(By.xpath(".//input[@name='gtin']"))
                .sendKeys("gtin");
        informationTab.findElement(By.xpath(".//input[@name='taric']"))
                .sendKeys("taric");
        informationTab.findElement(By.xpath(".//input[@name='weight']")).clear();
        informationTab.findElement(By.xpath(".//input[@name='weight']")).sendKeys("1");
        informationTab.findElement(By.xpath(".//input[@name='dim_x']")).sendKeys("1");
        informationTab.findElement(By.xpath(".//input[@name='dim_y']")).sendKeys("1");
        informationTab.findElement(By.xpath(".//input[@name='dim_z']")).sendKeys("1");
        informationTab.findElement(By.xpath(".//textarea[@name='attributes[en]']")).sendKeys("attributes");
    }
}