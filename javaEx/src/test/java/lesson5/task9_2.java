package lesson5;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class task9_2 {
    private WebDriver driver;

    @Before
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void testForTask9_2(){
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        clickCountries();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

    private void clickCountries(){
        List<WebElement> rows = driver.findElements(By.xpath(".//table[@class='dataTable']//tr[@class='row']"));
        List<String> listOfZones = new ArrayList<>();
        for (WebElement row: rows){
                listOfZones.add(row.findElement(By.xpath(".//td[3]/a")).getText());
        }
        for (String listOfZone: listOfZones) {
            driver.findElement(By.xpath(".//table[@class='dataTable']//tr[@class='row']/td[3]/a[text()='"+ listOfZone +"']")).click();
            checkZonesOrder();
            driver.findElement(By.xpath(".//button[@name='cancel']")).click();
        }
    }

    private void checkZonesOrder(){
        List <String> zones = new ArrayList<>();
        List <String> zonesOrdered = new ArrayList<>();
        List<WebElement> listOfZones = driver.findElements(By.xpath(".//table[@id='table-zones']//tr/td[3]"));
        for (WebElement listOfZone: listOfZones) {
            String selectedCountry = listOfZone
                    .findElement(By.xpath(".//select/option[@selected='selected']")).getText();
            zones.add(selectedCountry);
            zonesOrdered.add(selectedCountry);
        }
        Collections.sort(zonesOrdered);
        for (int i = 0; i < zonesOrdered.size(); i++){
            Assert.assertTrue(zonesOrdered.get(i).equals(zones.get(i)));
        }
    }
}