package tests;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class AdditionTest {

	private AndroidDriver driver;
	private WebDriverWait wait;
	
	final String one = "com.simplemobiletools.calculator:id/btn_1";
	final String two = "com.simplemobiletools.calculator:id/btn_2";
	final String three = "com.simplemobiletools.calculator:id/btn_3";
	final String four = "com.simplemobiletools.calculator:id/btn_4";
	final String five = "com.simplemobiletools.calculator:id/btn_5";
	final String six = "com.simplemobiletools.calculator:id/btn_6";
	final String seven = "com.simplemobiletools.calculator:id/btn_7";
	final String eight = "com.simplemobiletools.calculator:id/btn_8";
	final String nine = "com.simplemobiletools.calculator:id/btn_9";
	final String zero = "com.simplemobiletools.calculator:id/btn_0";
	final String neg = "com.simplemobiletools.calculator:id/btn_minus";
	final String div = "com.simplemobiletools.calculator:id/btn_divide";
	final String eq = "com.simplemobiletools.calculator:id/btn_equals";
	final String add = "com.simplemobiletools.calculator:id/btn_plus";
	final String dec = "com.simplemobiletools.calculator:id/btn_decimal";
	final String clr = "com.simplemobiletools.calculator:id/btn_clear";
	final String res = "com.simplemobiletools.calculator:id/result";
	@BeforeClass
	public void setup() throws Exception {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appium:platformVersion", "15");
		capabilities.setCapability("appium:deviceName", "emulator-5554");
		capabilities.setCapability("appium:automationName", "UiAutomator2");
		capabilities.setCapability("appium:app", "C:\\Users\\Asus\\Downloads\\simplacalculator.apk");
		capabilities.setCapability("appium:appPackage", "com.simplemobiletools.calculator");
		capabilities.setCapability("appium:appActivity", ".activities.SplashActivity.Orange");
		
		driver = new AndroidDriver(new URL("http://192.168.56.1:4723"), capabilities);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public void click(String id) {
		wait.until(driver -> driver.findElement(By.id(id))).click();
	}

	public void clear() {
		driver.findElement(By.id(clr)).click();
		WebElement clearBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id(clr)));
		clearBtn.click();
	}

    @Test(priority=1)
    public void Test() {

       
    }
    
	@BeforeMethod
	public void resetApp() throws InterruptedException {
		driver.terminateApp("com.simplemobiletools.calculator");
		driver.activateApp("com.simplemobiletools.calculator");
		Thread.sleep(2000);

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}