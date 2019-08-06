package lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckoutPage extends Page {
    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//button[@name='remove_cart_item']")
    private WebElement remove;

    public CheckoutPage removeProductsFromTable(){
        List<WebElement> productsInTable = driver.findElements(By.xpath(".//table[@class='dataTable rounded-corners']//tr/td[@class='item']"));
        for (WebElement product: productsInTable) {
            for (int i = 0; i < 30; i ++){
                try{
                    wait.until(ExpectedConditions.visibilityOf(remove));
                    remove.click();
                    break;
                }
                catch (Exception e){
                    System.out.println("Remove was not performed.");
                }
            }
            wait.until(ExpectedConditions.invisibilityOf(product));
        }
        return this;
    }
}
