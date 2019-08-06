package lesson11.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends Page {
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//button[@name='add_cart_product']")
    private WebElement addCartButton;

    @FindBy(xpath = ".//span[@class='quantity']")
    private WebElement quantityOfItems;

    @FindBy(xpath = ".//a[text()='Checkout »']")
    private WebElement checkOut;

    @FindBy(xpath = ".//a[text()='Home']")
    private WebElement homeLink;

    private void addToCart(){
        if (driver.findElements(By.xpath(".//select[@name='options[Size]']")).size() > 0){
            driver.findElement(By.xpath(".//select[@name='options[Size]']/option[2]")).click();
            driver.findElement(By.xpath(".//button[@name='add_cart_product']")).click();
        }
        else {
            addCartButton.click();
        }
    }

    public ProductDetailsPage addToCartAndCheckCartNumber(){
        String cartNumber = quantityOfItems.getText();
        addToCart();
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(".//span[@class='quantity']"),cartNumber));
        Assert.assertFalse(cartNumber.equals(quantityOfItems.getText()));
        return this;
    }

    public MainPage clickReturnHome(){
        homeLink.click();
        return new MainPage(driver);
    }

    public CheckoutPage clickCheckout(){
        checkOut.click();
        return new CheckoutPage(driver);
    }
}
