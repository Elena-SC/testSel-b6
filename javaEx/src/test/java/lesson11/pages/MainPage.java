package lesson11.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "li[class^='product']")
    private List<WebElement> products;

    public MainPage getToMainPage(){
        driver.get("http://litecart.stqa.ru/en/");
        return this;
    }

    public ProductDetailsPage openFirstProduct() {
        WebElement first = products.get(0);
        first.click();
        return new ProductDetailsPage(driver);
    }

    public ProductDetailsPage openSecondProduct() {
        WebElement second = products.get(1);
        second.click();
        return new ProductDetailsPage(driver);
    }

    public ProductDetailsPage openThirdProduct() {
        WebElement third = products.get(2);
        third.click();
        return new ProductDetailsPage(driver);
    }
}
