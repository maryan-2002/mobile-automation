package tests;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

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
    final String add = "com.simplemobiletools.calculator:id/btn_plus";
    final String eq = "com.simplemobiletools.calculator:id/btn_equals";
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

    // ================= TEST CASES =================

    @Test
    public void addTwoPositiveIntegers() { // TC022
        click(three);
        click(add);
        click(two);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "5");
    }

    @Test
    public void addTwoNegativeIntegers() { // TC023
        click(neg);
        click(six);
        click(add);
        click(neg);
        click(five);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "-11");
    }

    @Test
    public void negativePlusPositive() { // TC024
        click(neg);
        click(five);
        click(zero);
        click(add);
        click(five);
        click(five);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "5");
    }

    @Test
    public void positivePlusNegative() { // TC025
        click(seven);
        click(add);
        click(neg);
        click(three);
        click(one);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "-24");
    }

    @Test
    public void zeroPlusPositive() { // TC026
        click(zero);
        click(add);
        click(eight);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "8");
    }

    @Test
    public void zeroPlusNegative() { // TC027
        click(zero);
        click(add);
        click(neg);
        click(seven);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "-7");
    }

    @Test
    public void positivePlusZero() { // TC028
        click(seven);
        click(add);
        click(zero);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "7");
    }

    @Test
    public void negativePlusZero() { // TC029
        click(neg);
        click(seven);
        click(add);
        click(zero);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "-7");
    }

    @Test
    public void zeroPlusZero() { // TC030
        click(zero);
        click(add);
        click(zero);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "0");
    }

    @Test
    public void integerPlusFloat() { // TC031
        click(seven);
        click(add);
        click(three);
        click(dec);
        click(one);
        click(four);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "10.14");
    }

    @Test
    public void floatPlusInteger() { // TC032
        click(three);
        click(dec);
        click(one);
        click(four);
        click(add);
        click(three);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "6.14");
    }

    @Test
    public void twoDecimalNumbers() { // TC033
        click(three);
        click(dec);
        click(one);
        click(four);
        click(add);
        click(one);
        click(dec);
        click(one);
        click(four);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "4.28");
    }

    @Test
    public void decimalPlusZero() { // TC034
        click(three);
        click(dec);
        click(one);
        click(four);
        click(add);
        click(zero);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "3.14");
    }

    @Test
    public void largeNumberAddition() { // TC035
        click(two);
        for(int i=0;i<7;i++){
            click(zero);
        }
        click(add);
        click(two);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "20,000,002");
    }

    @Test
    public void smallDecimalAddition() { // TC036
        click(zero);
        click(dec);
        for(int i=0;i<6;i++){
            click(zero);
        }
        click(two);
        click(add);
        click(two);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "2.0000002");
    }

    @Test
    public void operatorFirst() { // TC037
        click(add);
        click(two);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "2");
    }

    @Test
    public void multipleOperators() { // TC038
        click(one);
        click(zero);
        click(add);
        click(add);
        click(one);
        click(zero);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "20");
    }

    @Test
    public void operatorWithEmptyInput() { // TC039
        click(add);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertTrue(result.equals("0") || result.isEmpty());
    }

    @Test
    public void multipleAdditionOperations() { // TC040
        click(three);
        click(zero);
        click(add);
        click(one);
        click(add);
        click(one);
        click(eq);

        String result = driver.findElement(By.id(res)).getText();
        Assert.assertEquals(result, "32");
    }
}