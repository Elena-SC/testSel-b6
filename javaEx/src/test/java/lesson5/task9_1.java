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

public class task9_1 {
    private WebDriver driver;

    @Before
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void testForTask9_1(){
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        checkCountriesOrder();
        clickZones();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

    private void checkCountriesOrder() {
        List <String> countries = new ArrayList<>();
        List <String> countriesOrdered = new ArrayList<>();
        List<WebElement> listOfCountries = driver.findElements(By.xpath(".//table[@class='dataTable']//tr[@class='row']/td[5]"));
        for (WebElement listOfCountry: listOfCountries){
            countries.add(listOfCountry.getText());
            countriesOrdered.add(listOfCountry.getText());
        }
        Collections.sort(countriesOrdered);
        for (int i = 0; i < countriesOrdered.size(); i++){
            Assert.assertTrue(countriesOrdered.get(i).equals(countries.get(i)));
        }
    }

    private void clickZones(){
        List<WebElement> rows = driver.findElements(By.xpath(".//table[@class='dataTable']//tr[@class='row']"));
        List<String> listOfZones = new ArrayList<>();
        for(WebElement row: rows){
            if (!row.findElement(By.xpath(".//td[6]")).getAttribute("innerText").equals("0")){
                listOfZones.add(row.findElement(By.xpath(".//td[5]/a")).getText());
            }
        }
        for (String listOfZone: listOfZones) {
            driver.findElement(By.xpath(".//table[@class='dataTable']//tr[@class='row']/td[5]/a[text()='"+ listOfZone +"']")).click();
            checkZonesOrder();
            driver.findElement(By.xpath(".//button[@name='cancel']")).click();
        }
    }

    private void checkZonesOrder(){
        List <String> zones = new ArrayList<>();
        List <String> zonesOrdered = new ArrayList<>();
        List<WebElement> listOfZones = driver.findElements(By.xpath(".//table[@id='table-zones']//tr/td[3]"));
        for (int i = 0; i < listOfZones.size() - 1; i++){
            zones.add(listOfZones.get(i).getText());
            zonesOrdered.add(listOfZones.get(i).getText());
        }
        Collections.sort(zonesOrdered);
        for (int i = 0; i < zonesOrdered.size(); i++){
            Assert.assertTrue(zonesOrdered.get(i).equals(zones.get(i)));
        }
    }
}