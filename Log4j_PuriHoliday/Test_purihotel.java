package com.max.Log4j_PuriHoliday;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
// import utils.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test_purihotel {
    public WebDriver driver;
    public final int IMPLICIT_WAIT_TIME = 10;
    public final int PAGE_LOAD_TIME = 5;
    ExtentSparkReporter sparkReporter;
    ExtentReports extent;
    ExtentTest test;

    // Local Drivers
    @BeforeMethod
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIME));
        driver.get("https://www.puriholidayresort.com/");
        sparkReporter = new ExtentSparkReporter("./reports/Holiday.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        test = extent.createTest("Holiday");
    }    
    

    // Portal Drivers
    // @BeforeMethod
    // public void startUp() throws MalformedURLException {
    //     DesiredCapabilities dc = new DesiredCapabilities();
    //     dc.setBrowserName("chrome");
    //     driver = new RemoteWebDriver(new URL("http://localhost:4444/"), dc);
    //     driver.manage().window().maximize();
    //     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIME));
    //     driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIME));
    //     driver.get("https://www.puriholidayresort.com/");
    //     WebDriverListener listener = new EventHandler();
    //     driver = new EventFiringDecorator<>(listener).decorate(driver);
    //     sparkReporter = new ExtentSparkReporter("./reports/Holiday.html");
    //     extent = new ExtentReports();
    //     extent.attachReporter(sparkReporter);
    //     test = extent.createTest("Holiday");
    // }

    @Test(priority = 0)
    public void test1() throws Throwable {
        By getPopUpLocator = By.xpath("//button[@type='button']");
        By getCheckinDateLocator = By.xpath("//input[@id='arr_datepicker']");
        By getCheckinDate = By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[4]/a");
        By getBookButtonLocator = By.xpath("//button[@class='custom-btn']");

        Thread.sleep(3000);
        driver.findElement(getPopUpLocator).click();

        driver.findElement(getCheckinDateLocator).click();

        Thread.sleep(3000);
        driver.findElement(getCheckinDate).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"dep_datepicker\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[6]/a")).click();

        Thread.sleep(3000);
        driver.findElement(getBookButtonLocator).click();
    }

    @Test(priority = 1)
    public void test2() throws Throwable {
        By getPopUpLocator = By.xpath("//button[@type='button']");
        By royalHoverLocator = By.xpath("//li[2]/a[contains(text(),'Rooms & Suites')]");
        By royalsuiteKeywordLocator = By.xpath("//a[contains(text(),'Royal Suite')]");
        By amenitiesDropdownLocator = By.xpath("//h3[contains(text(),'AMENITIES')]");

        Thread.sleep(3000);
        driver.findElement(getPopUpLocator).click();

        Thread.sleep(3000);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(royalHoverLocator)).build().perform();

        Thread.sleep(3000);
        driver.findElement(royalsuiteKeywordLocator).click();

        Thread.sleep(3000);
        driver.findElement(amenitiesDropdownLocator).click();
    }

    @Test(priority = 2)
    public void test3() throws Throwable {
        By getPopUpLocator = By.xpath("//button[@type='button']");
        By navpuriLocator = By.xpath("//li[1]/a[contains(text(),'About Puri')]");
        By dropChilikLocator = By.xpath("//a[contains(text(),'Chilika Lake')]");
        By continLocator = By.xpath(
                "//textarea[contains(text(),'Title your review - Describe your stay in one sentence or less. ')]");
        By butconLocator = By.xpath("//input[@value='Continue']");
        By familyLocator = By.xpath("//span[contains(text(),'Family')]");

        Thread.sleep(3000);
        driver.findElement(getPopUpLocator).click();

        Thread.sleep(3000);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(navpuriLocator)).build().perform();

        Thread.sleep(3000);
        driver.findElement(dropChilikLocator).click();

        Thread.sleep(4000);
        driver.findElement(continLocator).click();
        driver.findElement(continLocator).sendKeys("Excellent");

        Thread.sleep(3000);
        driver.findElement(butconLocator).click();

        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        Thread.sleep(3000);
        driver.findElement(familyLocator).click();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
        extent.flush();
    }
}
