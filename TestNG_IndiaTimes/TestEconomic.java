package com.max.TestNG_IndiaTimes;

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
import jdk.internal.org.jline.utils.Log;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
// import utils.EventHandler;

public class TestEconomic {
    public static WebDriver driver;
    private WebDriverWait wait;
    public final int IMPLICIT_WAIT_TIME = 10;
    public final int PAGE_LOAD_TIME = 10;

    // Local Driver
    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://economictimes.indiatimes.com/et-now/results");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        System.out.println("Browser");
    }
    // Portal Drivers
    // @BeforeMethod
    // public void beforeMethod() throws MalformedURLException {
    // ChromeOptions chromeOptions = new ChromeOptions();
    // driver = new RemoteWebDriver(new URL("http://localhost:4444"),
    // chromeOptions);
    // driver.manage().window().maximize();
    // wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    // driver.get("https://economictimes.indiatimes.com/et-now/results");
    // wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    // WebDriverListener listener = new EventHandler();
    // driver = new EventFiringDecorator<>(listener).decorate(driver);
    // System.out.println("Browser");
    // }

    @Test
    public void main() throws InterruptedException {
        WebElement ele1 = driver.findElement(By.linkText("Mutual Funds"));
        Thread.sleep(20000);
        // Scroll down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        ele1.click();
        Thread.sleep(5000);
        js.executeScript("window.scrollBy(0,1000)");
        Thread.sleep(5000);
        WebElement amcSelection = driver.findElement(By.xpath("//*[@id=\"amcSelection\"]"));
        amcSelection.click();
        Select select = new Select(amcSelection);
        select.selectByVisibleText(("Canara Robeco"));
        // Thread.sleep(10000);
        WebElement schemeselection = driver.findElement(By.xpath("//*[@id=\"schemenm\"]"));
        schemeselection.click();
        Select select1 = new Select(schemeselection);

        select1.selectByVisibleText("Canara Robeco Bluechip Equity Direct-G");
        WebElement getDetails = driver.findElement(By.id("anchor3"));
        getDetails.click();
        Set<String> Handles = driver.getWindowHandles();
        for (String handle : Handles) {
            if (!handle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(handle);
                break;
            }
        }
        // Thread.sleep(15000);
        WebElement Type = driver.findElement(By.id("installment_type"));
        Type.click();
        // Thread.sleep(10000);
        WebElement SIP = driver.findElement(By.cssSelector("li[data-value='sip']"));
        SIP.click();
        // Thread.sleep(10000);
        WebElement amt = driver.findElement(By.id("installment_amt"));
        amt.click();
        // Thread.sleep(10000);
        WebElement value = driver.findElement(By.cssSelector("li[data-value='1000']"));
        value.click();
        // Thread.sleep(10000);
        WebElement period = driver.findElement(By.id("installment_period"));
        period.click();
        // Thread.sleep(10000);
        WebElement value1 = driver.findElement(By.cssSelector("li[data-value='1095']"));
        value1.click();
        // Thread.sleep(10000);
        WebElement returns = driver.findElement(By.cssSelector("li[data-offset='mfReturnsOffset']"));
        returns.click();
        // Thread.sleep(10000);
        List<WebElement> allRows = driver.findElements(By.tagName("tr"));
        WebElement currentRow = allRows.get(1);
        List<WebElement> cells = currentRow.findElements(By.tagName("td"));
        for (WebElement e : cells) {
            System.out.print(e.getText() + " ");
        }
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        // Thread.sleep(15000);
        driver.quit();
    }
}