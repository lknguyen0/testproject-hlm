import com.epam.healenium.appium.wrapper.DriverWrapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static org.junit.Assert.assertTrue;

import java.net.URL;

public class testSteps {

    private static AppiumDriver driver;

    @SneakyThrows
    @BeforeMethod
    public static void setUp() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");

        //for the second test run, uninstall the app from the emulator and change app path to .../myapp-button_login_1.apk

        caps.setCapability(MobileCapabilityType.APP, "https://github.com/lknguyen0/testproject-hlm/raw/master/apps/myapp-original.apk");
        caps.setCapability("autoLaunch", false);
        URL url = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(url, caps);
        driver = DriverWrapper.wrap(driver);
    }

    @SneakyThrows
    @Test
    public void navigateToLoginPage() {
        driver.launchApp();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("text_greeting")));
        boolean isLoginButtonDisplayed = driver.findElement(MobileBy.AccessibilityId("button_login")).isDisplayed();
        assertTrue(isLoginButtonDisplayed);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}


