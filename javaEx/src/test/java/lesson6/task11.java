package lesson6;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class task11 {
    private WebDriver driver;
    private Random random = new Random();
    private String emailAddress;
    private final String password = "password";
    private final String firstName = "firstname";
    private final String lastName = "lastname";

    @Before
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void testForTask11() {
        getToRegistrationPage();
        selectCountry();
        fillMandatoryFields();
        clickCreateAccount();
        checkSuccessCreateAccount();
        logout();
        login();
        checkSuccessLogin();
        logout();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

    private void getToRegistrationPage(){//not sure - do we need go to the registration page from the very beginning?
                                         //if yes - this 3 lines should be replaced with one line with the appropriate link
        driver.get("http://litecart.stqa.ru/en/");
        driver.findElement(By.xpath(".//a[text()='New customers click here']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("#create-account")).isDisplayed());
    }

    private void logout(){
        driver.findElement(By.xpath(".//a[text()='Logout']")).click();
    }

    private void login(){
        driver.findElement(By.cssSelector("[name='email']")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("[name='login']")).click();
    }

    private void fillMandatoryFields(){
        driver.findElement(By.cssSelector("[name=firstname]")).sendKeys(firstName);
        driver.findElement(By.cssSelector("[name=lastname]")).sendKeys(lastName);
        driver.findElement(By.cssSelector("[name=address1]")).sendKeys("address1");
        driver.findElement(By.cssSelector("[name=postcode]")).sendKeys("12345");
        driver.findElement(By.cssSelector("[name=city]")).sendKeys("city");
        driver.findElement(By.cssSelector("[name=phone]")).sendKeys("+111");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("[name=confirmed_password]")).sendKeys(password);
        WebElement emailField = driver.findElement(By.cssSelector("[name=email]"));
        emailAddress=random.nextInt(10000)+"@1.1";
        emailField.sendKeys(emailAddress);
        driver.findElement(By.xpath(".//select[@name='zone_code']/option["+random.nextInt(22)+"]")).click();
    }

    private void selectCountry(){
        driver.findElement(By.cssSelector(".select2-selection__arrow")).click();
        WebElement inputCountryField = driver.findElement(By.cssSelector(".select2-search__field"));
        inputCountryField.sendKeys("United States");
        inputCountryField.sendKeys(Keys.ENTER);
    }

    private void clickCreateAccount() {
        driver.findElement(By.cssSelector("[name='create_account']")).click();
    }

    private void checkSuccessCreateAccount() {
        driver.findElement(By.xpath(".//div[@class='notice success'][text()=' Your customer account has been created.']"))
                .isDisplayed();
    }

    private void checkSuccessLogin(){
        driver.findElement(By.xpath(".//div[@class='notice success']" +
                "[text()=' You are now logged in as "+ firstName +" "+ lastName +".']")).isDisplayed();
    }
}
