package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;


import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void registration(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void nameCheck() {
        driver.get("http://localhost/litecart/");
        wait.until(titleIs("Online Store | My Store"));
        WebElement registrationLink = driver.findElement(By.xpath("//a[text()='New customers click here']"));
        registrationLink.click();
        wait.until(titleIs("Create Account | My Store"));

        String firstName = "Test";
        String lastName = "Tester";
        String address = "Street 123";
        String postCode = "54321";
        String city = "Springfield";
        String country = "United States";
        String state = "Massachusetts";
        String eMail = String.valueOf(System.currentTimeMillis()) + "@mail.ru";
        String phone = "413-205-3505";
        String password = "password123";

        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        WebElement lastNameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
        WebElement addressElement = driver.findElement(By.xpath("//input[@name='address1']"));
        WebElement postCodeElement = driver.findElement(By.xpath("//input[@name='postcode']"));
        WebElement cityElement = driver.findElement(By.xpath("//input[@name='city']"));
        WebElement countryElement = driver.findElement(By.xpath("//span[@role='presentation']"));
        Select zoneCodeElement = new Select(driver.findElement(By.xpath("//select[@name='zone_code']")));
        WebElement emailElement = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement phoneElement = driver.findElement(By.xpath("//input[@name='phone']"));
        WebElement newsLetterElement = driver.findElement(By.xpath("//input[@name='newsletter']"));
        WebElement passwordElement = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement confirmedPasswordElement = driver.findElement(By.xpath("//input[@name='confirmed_password']"));
        WebElement submit = driver.findElement(By.xpath("//button[@name='create_account']"));

        firstNameElement.sendKeys(firstName);
        lastNameElement.sendKeys(lastName);
        addressElement.sendKeys(address);
        postCodeElement.sendKeys(postCode);
        cityElement.sendKeys(city);
        countryElement.click();
        WebElement countryInTheListElement = driver.findElement(By.xpath("//li[text()='United States']"));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        countryInTheListElement.click();
        zoneCodeElement.selectByValue("MA");
        emailElement.sendKeys(eMail);
        phoneElement.sendKeys(phone);
        if (newsLetterElement.getAttribute("checked") != null) newsLetterElement.click();
        passwordElement.sendKeys(password);
        confirmedPasswordElement.sendKeys(password);
        submit.click();


        WebElement logoutLink = driver.findElement(By.xpath("//a[text()='Logout']"));
        logoutLink.click();
        wait.until(titleIs("Online Store | My Store"));

        WebElement loginEmail = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement loginPassword = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@name='login']"));
        loginEmail.sendKeys(eMail);
        loginPassword.sendKeys(password);
        loginButton.click();

        WebElement checkLogoutLink = driver.findElement(By.xpath("//a[text()='Logout']"));
        checkLogoutLink.click();
    }

    @After
    public void stop(){
        driver.quit();
        driver= null;
    }
}