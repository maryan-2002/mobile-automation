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

public class DivisionTest {

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
		capabilities.setCapability("appium:deviceName", "emulator-5556");
		capabilities.setCapability("appium:automationName", "UiAutomator2");
		capabilities.setCapability("appium:app", "C:\\Users\\maria\\Downloads\\simplecalculator.apk");
		capabilities.setCapability("appium:appPackage", "com.simplemobiletools.calculator");
		capabilities.setCapability("appium:appActivity", ".activities.SplashActivity.Orange");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4729/wd/hub"), capabilities);
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
    public void divisionOfTwoPositiveIntegers() {

    	 click(five);
         click(div);
         click(five);
         click(eq);

         String result = driver.findElement(By.id(res)).getText();
        System.out.println("Result is: " + result);
        Assert.assertEquals(result, "1");
    }
    @Test(priority=2)
    public void divisionOfTwoNegativeIntegers() {
    	clear();
    	click(neg);//minus
        click(five);//5
        click(div);//div
		  click(neg);//minus
        click(five);//5
        click(eq);//=

        String result = driver.findElement(By.id(res)).getText();
        System.out.println("Result is: " + result);
        Assert.assertEquals(result, "1");
    }

	@Test(priority = 3)
	public void divisionOfOneNegativeByOtherPositvieInteger() {
		clear();
		click(neg);// minus
		click(five);// 5
		click(div);// div
		click(five);// 5
		click(eq);// =

		String result = driver.findElement(By.id(res)).getText();
		System.out.println("Result is: " + result);
		Assert.assertEquals(result, "-1");
	}
	@Test(priority =4)
	public void divisionOfOnePositvieByOtherNegativeInteger() {
		clear();
		click(five);// 5
		click(div);// div
		click(neg);// minus
		click(five);// 5
		click(eq);// =
		
		String result = driver.findElement(By.id(res)).getText();
		System.out.println("Result is: " + result);
		Assert.assertEquals(result, "-1");
	}
	
	@Test(priority =5)
	public void divideZeroByPositiveNumber() {
		clear();
		click(zero);// 0
		click(div);// div
		click(five);// 5
		click(eq);// =

		String result = driver.findElement(By.id(res)).getText();
		System.out.println("Result is: " + result);
		Assert.assertEquals(result, "0");
	}

	@Test(priority =6)
	public void divideZeroByNegativeNumber() {
		clear();
		click(zero);// 0
		click(div);// div
		click(neg);// minus

		click(five);// 5
		click(eq);// =

		String result = driver.findElement(By.id(res)).getText();
		System.out.println("Result is: " + result);
		Assert.assertEquals(result, "0");
	}
	
	@Test(priority =7)
	public void dividePositiveByZero() {
		clear();
		click(five);// 5		
		click(div);// div
		click(zero);// 0
		click(eq);// =
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement toast = wait.until(
		        ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast"))
		);

		String toastText = toast.getText();

		System.out.println(toastText);
		Assert.assertEquals(toastText, "Error: division by zero");
	}
	@Test(priority =8)
	public void divideNegativeByZero() {
		clear();
		click(neg);// minus
		click(five);// 5		
		click(div);// div
		click(zero);// 0
		click(eq);// =
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement toast = wait.until(
		        ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast"))
		);

		String toastText = toast.getText();

		System.out.println(toastText);
		Assert.assertEquals(toastText, "Error: division by zero");
	}
	@Test(priority =9)
	public void divideZeroByZero() {
		clear();
		
		click(zero);// 0		
		click(div);// div

		click(zero);// 0

		click(eq);// =
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement toast = wait.until(
		        ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast"))
		);

		String toastText = toast.getText();

		System.out.println(toastText);
		Assert.assertEquals(toastText, "Error: division by zero");
	}
	@Test(priority =10)
	public void divideIntegerNumberByDecimalNumber() {
		clear();
		
		click(five);// 5
		click(div);// div
		click(two);// 2
		click(dec);// .
		click(five);// 5
		click(eq);// =

		String result = driver.findElement(By.id(res)).getText();
		System.out.println("Result is: " + result);
		Assert.assertEquals(result, "2");
	}
	@Test(priority =11)
	public void divideDecimalNumberByIntegerNumber() {
		clear();
		
		click(five);// 5
		click(dec);// .
		click(five);// 5
		click(div);// div
		click(two);// 2
		click(eq);// =

		String result = driver.findElement(By.id(res)).getText();
		System.out.println("Result is: " + result);
		Assert.assertEquals(result, "2.75");
	}
	@Test(priority =12)
	public void divideTwoDecimals() {
		clear();
		
		click(five);// 5
		click(dec);// .
		click(five);// 5
		click(div);// div
		click(two);// 2
		click(dec);// .
		click(five);// 5
		click(eq);// =

		String result = driver.findElement(By.id(res)).getText();
		System.out.println("Result is: " + result);
		Assert.assertEquals(result, "2.2");
	}
	
	@Test(priority =13)
	public void divideDecimalByZero() {
		clear();
		
		click(five);// 5
		click(dec);// .
		click(five);// 5
		click(div);// div
		click(zero);// 0
		click(eq);// =

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement toast = wait.until(
		        ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast"))
		);

		String toastText = toast.getText();

		System.out.println(toastText);
		Assert.assertEquals(toastText, "Error: division by zero");
	}
	
	@Test(priority =14)
	public void divideLargeNum() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		clear();
		
		click(one);// 1
		click(zero);
		click(zero);
		click(zero);
		click(zero);
		click(zero);
		click(zero);
		click(zero);
		click(zero);
		click(div);
		click(one);
		click(eq);// =

		String result = driver.findElement(By.id(res)).getText();
		System.out.println("Result is: " + result);
		Assert.assertEquals(result, "100,000,000");
		
		}
	
	@Test(priority =15)
	public void divideSmallDecimal() {
		clear();
		
		click(zero);
		click(dec);
		click(zero);
		click(zero);
		click(zero);
		click(zero);
		click(zero);
		click(zero);
		click(zero);
		click(one);
		click(div);
		click(one);
		click(zero);
		click(eq);// =

		String result = driver.findElement(By.id(res)).getText();
		System.out.println("Result is: " + result);
		Assert.assertEquals(result, "0.000000001");
		
		}
	
	@Test(priority =16)
	public void opFirst() {
		clear();
		click(clr);
		click(div);// div
		click(one);
		click(eq);// =

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement toast = wait.until(
		        ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast"))
		);

		String toastText = toast.getText();

		System.out.println(toastText);
		Assert.assertEquals(toastText, "Invalid user format");
	}
	
	@Test(priority =17)
	public void multipleOp() {
		clear();
		click(one);
		click(zero);
		click(div);// div
		click(div);// div
		click(one);
		click(zero);
		click(eq);// =

		String result = driver.findElement(By.id(res)).getText();
		System.out.println("Result is: " + result);
		Assert.assertEquals(result, "1");
		
	}

	@Test(priority =18)
	public void opOnly() {
		clear();
		click(clr);
		click(div);// div
		click(eq);// =

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement toast = wait.until(
		        ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast"))
		);

		String toastText = toast.getText();

		System.out.println(toastText);
		Assert.assertEquals(toastText, "Invalid user format");
	}
	
	@Test(priority =19)
	public void resultWmanyDecimals() {
		clear();
		click(one);
		click(zero);
		click(div);// div
		click(three);
		click(eq);// =

		String result = driver.findElement(By.id(res)).getText();
		System.out.println("Result is: " + result);
		Assert.assertEquals(result, "3.333333333333333333333333333333333");
		
	}
	@Test(priority =20)
	public void floatingPrecision() {
		clear();
		click(one);
		click(div);// div
		click(three);
		click(eq);

		String result = driver.findElement(By.id(res)).getText();
		System.out.println("Result is: " + result);
		Assert.assertEquals(result, "0.3333333333333333333333333333333333");
		
	}
	@Test(priority =21)
	public void dividingMultipleDivisions() {
		clear();
		click(two);
		click(zero);
		click(div);// div
		click(two);
		click(div);// div
		click(two);
		click(eq);// =

		String result = driver.findElement(By.id(res)).getText();
		System.out.println("Result is: " + result);
		Assert.assertEquals(result, "5");
		
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