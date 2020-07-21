package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void nameCheck() {
        driver.get("http://localhost/litecart/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait.until(titleIs("Online Store | My Store"));
        WebElement duck = driver.findElement(By.xpath("//h3[contains(text(),'Campaigns')]/following::li[contains(@class,product)]"));
        String duckName = duck.findElement(By.xpath(".//div[@class=\"name\"]")).getText();
        duck.click();
        String productPageDuckName = driver.findElement(By.xpath("//h1[@class=\"title\"]")).getText();
        Assert.assertEquals(duckName,productPageDuckName);
    }

    @Test
    public void priceCheck() {
        driver.get("http://localhost/litecart/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait.until(titleIs("Online Store | My Store"));
        WebElement duck = driver.findElement(By.xpath("//h3[contains(text(),'Campaigns')]/following::li[contains(@class,product)]"));
        String regularPrice = duck.findElement(By.xpath(".//s[@class='regular-price']")).getText();
        String campaignPrice = duck.findElement(By.xpath(".//strong[@class='campaign-price']")).getText();
        duck.click();
        wait.until(titleIs("Yellow Duck | Subcategory | Rubber Ducks | My Store"));
        WebElement information = driver.findElement(By.xpath("//div[@class='information']"));
        String updatedRegularPrice = information.findElement(By.xpath(".//s[@class='regular-price']")).getText();
        String updatedCampaignPrice = information.findElement(By.xpath(".//strong[@class='campaign-price']")).getText();
        Assert.assertEquals(regularPrice,updatedRegularPrice);
        Assert.assertEquals(campaignPrice,updatedCampaignPrice);
    }

    @Test
    public void pricesColorsAndSizesCheck() {
        driver.get("http://localhost/litecart/");
        wait.until(titleIs("Online Store | My Store"));
        WebElement duck = driver.findElement(By.xpath("//h3[contains(text(),'Campaigns')]/following::li[contains(@class,product)]"));
        String regularColor = duck.findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("color");
        String campaignColor = duck.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("color");
        String[] regularColorArray = regularColor.replaceAll("[rgba()]", "").split(", ");
        String[] campaignColorArray = campaignColor.replaceAll("[rgba()]", "").split(", ");
        Assert.assertEquals(regularColorArray[0], regularColorArray[1]);
        Assert.assertEquals(regularColorArray[0], regularColorArray[2]);
        Assert.assertEquals(campaignColorArray[1], "0");
        Assert.assertEquals(campaignColorArray[2], "0");
        Assert.assertEquals("line-through",  duck.findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("text-decoration-line"));
        Assert.assertEquals("700",  duck.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-weight"));
        String regularFontSize = duck.findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("font-size").replaceAll("px", "");
        String campaignFontSize = duck.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-size").replaceAll("px", "");
        Assert.assertTrue(Double.parseDouble(regularFontSize) < Double.parseDouble(campaignFontSize));
        duck.click();
        wait.until(titleIs("Yellow Duck | Subcategory | Rubber Ducks | My Store"));
        WebElement information = driver.findElement(By.xpath("//div[@class='information']"));
        WebElement updatedRegularPrice = information.findElement(By.xpath(".//s[@class='regular-price']"));
        WebElement updatedCampaignPrice = information.findElement(By.xpath(".//strong[@class='campaign-price']"));
        String updatedRegularColor = updatedRegularPrice.getCssValue("color");
        String updatedCampaignColor = updatedCampaignPrice.getCssValue("color");
        String updatedRegularFontSize = updatedRegularPrice.getCssValue("font-size").replaceAll("px", "");
        String updatedCampaignFontSize = updatedCampaignPrice.getCssValue("font-size").replaceAll("px", "");
        String[] updatedRegularColorArray = updatedRegularColor.replaceAll("[rgba()]", "").split(", ");
        String[] updatedCampaignColorArray = updatedCampaignColor.replaceAll("[rgba()]", "").split(", ");
        Assert.assertEquals(updatedRegularColorArray[0], updatedRegularColorArray[1]);
        Assert.assertEquals(updatedRegularColorArray[0], updatedRegularColorArray[2]);
        Assert.assertEquals(updatedCampaignColorArray[1], "0");
        Assert.assertEquals(updatedCampaignColorArray[2], "0");
        Assert.assertEquals("line-through",  information.findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("text-decoration-line"));
        Assert.assertEquals("700",  information.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-weight"));
        Assert.assertTrue(Double.parseDouble(updatedRegularFontSize) < Double.parseDouble(updatedCampaignFontSize));
    }

    @After
    public void stop(){
        driver.quit();
        driver= null;
    }
}