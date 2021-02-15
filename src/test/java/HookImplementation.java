import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class HookImplementation {


    public static Logger LOGGER = LoggerFactory.getLogger(HookImplementation.class);
    public static AppiumDriver<MobileElement> driver;
    public static WebDriverWait wait;
    public static URL url;
    public static DesiredCapabilities capabilities;
    public static Boolean localAndroid = true;


    @BeforeScenario
    public void setup() throws MalformedURLException {


        if (localAndroid) {


            url = new URL("http://0.0.0.0:4723/wd/hub");
            capabilities = new DesiredCapabilities();

            capabilities.setCapability("deviceName", "Samsung Note4");
            capabilities.setCapability("platformVersion", "6.0");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("unicodeKeyboard", "false");
            capabilities.setCapability("noReset", "true");  //noReset just clears the app data, such as its cache.
            //capabilities.setCapability("fullReset", "true");  //fullRest uninstalls the app. with app ipa,apk
            //capabilities.setCapability("udid", "410027a3457c9183");
            capabilities.setCapability("udid","emulator-5554");
            //capabilities.setCapability("appPackage", "com.marketyo.platform");
            //capabilities.setCapability("appActivity", "com.marketyo.platform.root.main.activities.landing.LandingActivity");

            capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/src/main/resources/getir-testing-case-study.apk");
            ///Users/mac/Downloads/android-ui-master/example.apk


            driver = new AndroidDriver(url, capabilities);
            wait = new WebDriverWait(driver, 10);


        } else {

            url = new URL("http://0.0.0.0:4723/wd/hub");
            capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.2");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11");
            capabilities.setCapability(MobileCapabilityType.APP, "//Users/mac/Library/Developer/Xcode/DerivedData/UIKitCatalog-dtgedxpidruoiwabarsvifjxcccw/Build/Products/Debug-iphonesimulator/UIKitCatalog.app");

            driver = new IOSDriver(url, capabilities);
            wait = new WebDriverWait(driver, 10);

        }


    }


    @AfterScenario
    public void close() {

        driver.quit();
    }
}
