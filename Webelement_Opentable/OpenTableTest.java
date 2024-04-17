package com.max.Webelement_Opentable;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Set;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
// import utils.EventHandler;

public class OpenTableTest {
    public static WebDriver driver;
    @SuppressWarnings("unused")
    private WebDriverWait wait;
    public final int IMPLICIT_WAIT_TIME = 10;
    public final int PAGE_LOAD_TIME = 5;
    
    // Local Drivers
    @BeforeMethod
    public WebDriver beforeMethod() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.opentable.com");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return driver;
    }

    // @BeforeMethod
    // public WebDriver beforeMethod() throws MalformedURLException {
    //     ChromeOptions chromeOptions = new ChromeOptions();
    //     driver = new RemoteWebDriver(new URL("http://localhost:4444/"), chromeOptions);
    //     driver.get("https://www.opentable.com");
    //     driver.manage().window().maximize();
    //     wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    //     WebDriverListener listener = new EventHandler();
    //     driver = new EventFiringDecorator<>(listener).decorate(driver);
    //     return driver;

    // }

    @Test
    public void opent() throws InterruptedException {
        Thread.sleep(10000);
        int numberOfArrowDownPresses = 3;
        int dropdown = 2;
        int timedropdown = 1;

        WebElement inputbox = driver.findElement(By.xpath("//input[@placeholder='Location, Restaurant, or Cuisine']"));
        inputbox.click();
        inputbox.sendKeys("Bangalore");

        for (int i = 0; i < numberOfArrowDownPresses; i++) {
            inputbox.sendKeys(Keys.ARROW_DOWN);
        }

        WebElement Letsgo = driver
                .findElement(By.xpath("/html/body/div[1]/div/div/main/header/div/span/div/div/div[2]/div[2]/button"));
        Letsgo.click();
        WebElement hotel = driver.findElement(
                By.xpath("/html/body/div[1]/div/div/main/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/a/h6"));
        hotel.click();

        String parentWindowHandle = driver.getWindowHandle();
        Thread.sleep(3000);
        WebElement imageElement = driver
                .findElement(By.xpath("//*[@id='mainContent']/div/div/div[2]/div/div[1]/a/img"));
        imageElement.click();

        System.out.println("CLicked First Hotel");

        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0, 300);");
                break;
            }
        }

        WebElement partySizeDropdown = driver
                .findElement(By.xpath("//select[@id='restProfileSideBarDtpPartySizePicker']"));
        partySizeDropdown.click();

        try {
            for (int i = 0; i < dropdown; i++) {
                partySizeDropdown.sendKeys(Keys.ARROW_DOWN);
            }
            partySizeDropdown.sendKeys(Keys.ENTER);
        } catch (Exception e) {

        }
        System.out.println("CLicked 4 persons");
        try {
            WebElement datePicker = driver.findElement(By.xpath("//*[@id='restProfileSideBarDtpDayPicker-label']"));
            datePicker.click();
            WebElement dateElement = driver.findElement(By.xpath(
                    "//*[@id='restProfileSideBarDtpDayPicker-wrapper']/div/div/div/table/tbody/tr[6]/td[7]/button"));
            dateElement.click();
            System.out.println("CLicked nov 4 2023");

        } catch (Exception e) {

        }

        // Time picker
        try {
            WebElement timePicker = driver.findElement(By.xpath("//*[@id='restProfileSideBartimePickerDtpPicker']"));
            timePicker.click();

            try {
                for (int i = 0; i < timedropdown; i++) {
                    timePicker.sendKeys(Keys.ARROW_UP);
                }
                timePicker.sendKeys(Keys.ENTER);
            } catch (Exception e) {

            }

            System.out.println("CLicked 6:30pm");

            WebElement findatime = driver
                    .findElement(By.xpath("//*[@id='mainContent']/div/div[2]/div[2]/div/article/div/div[5]/button"));
            findatime.click();
            System.out.println("CLicked Find a Time btn");
        } catch (Exception e) {

        }
        WebElement signinclick = driver
                .findElement(By.xpath("/html/body/div[1]/div/div/header/div[2]/div[2]/div[1]/button"));
        signinclick.click();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

}