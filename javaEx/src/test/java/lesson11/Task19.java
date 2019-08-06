package lesson11;

import lesson11.pages.CheckoutPage;
import lesson11.pages.MainPage;
import lesson11.pages.ProductDetailsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task19 {
    private WebDriver driver;
    private MainPage mainpage;
    private CheckoutPage checkoutpage;
    private ProductDetailsPage productdetailspage;

    @Before
    public void start() {
        driver = new ChromeDriver();
        mainpage = new MainPage(driver);
        checkoutpage = new CheckoutPage(driver);
        productdetailspage = new ProductDetailsPage(driver);
    }

    @Test
    public void testForTask19(){
        mainpage
                .getToMainPage()
                .openFirstProduct();
        productdetailspage
                .addToCartAndCheckCartNumber()
                .clickReturnHome();
        mainpage
                .openSecondProduct();
        productdetailspage
                .addToCartAndCheckCartNumber()
                .clickReturnHome();
        mainpage
                .openThirdProduct();
        productdetailspage
                .addToCartAndCheckCartNumber()
                .clickCheckout();
        checkoutpage
                .removeProductsFromTable();
    }

    @After
    public void tearDown(){
        driver.quit();
        driver = null;
    }
}
