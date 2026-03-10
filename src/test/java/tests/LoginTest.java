package tests;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class LoginTest {

    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:platformVersion", "13.0");
        capabilities.setCapability("appium:deviceName", "emulator-5556");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:app", "C:\\Users\\maria\\Downloads\\LoginDemo.apk");
        

        System.out.println("Emulator ...... ");
        Thread.sleep(2000); 
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void loginTest() {

        try {
            WebElement username = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("com.zappycode.logindemo:id/etName"))
            );
            username.sendKeys("User");

            WebElement password = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("com.zappycode.logindemo:id/etPassword"))
            );
            password.sendKeys("123456");

            WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("com.zappycode.logindemo:id/btnLogin"))
            );
            loginBtn.click();

        } catch (Exception e) {
            System.out.println("loginTest issue is : " + e.getMessage());
        }

        
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}