import com.thoughtworks.gauge.Step;
import element.ReadFiles;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;



import java.util.ArrayList;
import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class StepImplementation extends HookImplementation {


    @Step({"<key> li elementi bul ve tıkla", "Click element by <key>"})
    public void clickByKey(String key) {
        clickElement(key);
    }

    @Step({"<key> li elementi bul, temizle ve <text> değerini yaz",
            "Find element by <key> clear and send keys <text>"})
    public void sendKeysByKey(String key, String text) {
        clearElement(key);
        sendKeys(key, text);
    }


    public void moveToBottom() {


        Dimension dim = driver.manage().window().getSize();
        int height = dim.getHeight();
        int width = dim.getWidth();
        int x = width / 2;
        int top_y = (int) (height * 0.80);
        int bottom_y = (int) (height * 0.20);
        //System.out.println("coordinates :" + x + "  " + top_y + " " + bottom_y);
        TouchAction ts = new TouchAction(driver);
        ts.press(point(x, top_y)).waitAction(waitOptions(ofMillis(2000))).moveTo(point(x, bottom_y)).release().perform();


    }


    @Step({"<seconds> saniye bekle ", "Wait <second> seconds"})
    public void waitBySecond(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    @Step("geri butonuna bas")
    public void clickBybackButton() {

        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));


    }


    public static void sendKeys(String elementValue, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (ReadFiles.readLocator(elementValue))).sendKeys(text);
        LOGGER.info(ReadFiles.readLocator(elementValue) + " is typed... ");

    }

    public static void clickElement(String elementValue) {

        wait.until(ExpectedConditions.visibilityOfElementLocated
                (ReadFiles.readLocator(elementValue))).click();
        LOGGER.info(ReadFiles.readLocator(elementValue) + " is clicked... ");
    }

    public static void clearElement(String elementValue) {

        wait.until(ExpectedConditions.visibilityOfElementLocated
                (ReadFiles.readLocator(elementValue))).clear();
        LOGGER.info(ReadFiles.readLocator(elementValue) + " is cleared... ");
    }

    @Step("Swipe vertically between <firstElement> to <secondElement>")
    public static void horizantalSwipeBetweenTwoElement(String firstElement, String secondElement) {


        MobileElement first = getMobileElement(firstElement);

        MobileElement second = getMobileElement(secondElement);


        int startX = first.getLocation().x + first.getSize().width / 2;
        int startY = first.getLocation().y + first.getSize().height / 2;

        System.out.println(startX + "  - " + startY);
        int secondX = second.getLocation().x + second.getSize().width / 2;
        int secondY = second.getLocation().y + second.getSize().height / 2;
        System.out.println(secondX + "  - " + secondY);

        new TouchAction(driver)
                .press(point(secondX, secondY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(startX, startY))
                .release().perform();

        LOGGER.info("-----horizantalSwipeBetweenTwoElement----");
    }


    public static MobileElement getMobileElement(String element) {
        LOGGER.info("All mobile elements are added to arraylist");
        return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (ReadFiles.readLocator(element)));


    }

    public static List<MobileElement> getMobileElements(String element) {

        List<MobileElement> mobileElementList = (List<MobileElement>) driver.findElements(ReadFiles.readLocator(element));
        LOGGER.info("All mobile elements are added to arraylist");
        return mobileElementList;
    }


    @Step("Scroll down to find to <countryName> and <element> selection")
    public void swipeToElementUntilIsVisible(String countryName, String element) {


        while (true) {
            List<MobileElement> mobileElementList = getMobileElements(element);
            List<String> countryNameList = new ArrayList<>();
            for (MobileElement mobileElement1 : mobileElementList) {

                System.out.println(mobileElement1.getText());
                countryNameList.add(mobileElement1.getText());
                if (mobileElement1.getText().equals(countryName) && mobileElement1.isDisplayed()) {
                    mobileElement1.click();

                }
            }
            if (!countryNameList.contains(countryName)) {
                moveToBottom();
            } else {
                break;
            }

        }
        LOGGER.info("swipeToElementUntilIsVisible");

    }

    public static void verticalSwipeBetweenTwoElement(MobileElement gorsel, MobileElement tumu) {


        int startX = tumu.getLocation().x + tumu.getSize().width / 2;
        int startY = tumu.getLocation().y + tumu.getSize().height / 2;
        int secondX = gorsel.getLocation().x + gorsel.getSize().width / 2;
        int secondY = gorsel.getLocation().y + gorsel.getSize().height / 2;


        new TouchAction(driver)
                .press(point(secondX, secondY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(startX, startY))
                .release().perform();


    }


    @Step("Swipe stories to left and right and verify swiping works correctly")
    public void swipeStoriesToLeftAndRight() {


        MobileElement carouselDescription = getMobileElement("carouselDescription");
        Assert.assertEquals(true, carouselDescription.isDisplayed());


        int startX = carouselDescription.getLocation().x + carouselDescription.getSize().width / 2;
        int startY = carouselDescription.getLocation().y + carouselDescription.getSize().height / 2;

        System.out.println(startX + "  - " + startY);
//to right
        new TouchAction(driver)
                .press(point(startX - 250, startY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(startX, startY))
                .release().perform();
//to left
        new TouchAction(driver)
                .press(point(startX + 250, startY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(startX, startY))
                .release().perform();
    }

    @Step("Select only <languages> from languages selections page")
    public void implementation1(String languages) {
        String icelandic = "Icelandic";

        while (true) {
            List<MobileElement> mobileElementList = getMobileElements(languages);
            List<String> countryNameList = new ArrayList<>();
            for (MobileElement mobileElement1 : mobileElementList) {

                System.out.println(mobileElement1.getText());
                countryNameList.add(mobileElement1.getText());
                if (mobileElement1.getText().equals(icelandic) && mobileElement1.isDisplayed()) {
                    mobileElement1.click();

                }
            }
            if (!countryNameList.contains(icelandic)) {
                moveToBottom();
            } else {
                break;
            }

        }
        LOGGER.info("swipeToElementUntilIsVisible");

    }


    @Step("Swipe left on <Feelgood> section books and click on the <index>th item from the list")
    public void implementation20(String section, int index) {


        MobileElement secondBook = getMobileElement("feelGoodSecondBook");
        Assert.assertEquals(true, secondBook.isDisplayed());

        int startX = secondBook.getLocation().x + secondBook.getSize().width / 2;
        int startY = secondBook.getLocation().y + secondBook.getSize().height / 2;

        System.out.println(startX + "  - " + startY);
//to right

        int count = index % 2;

        for (int i = 0; i <= count; i++) {
            new TouchAction(driver)
                    .press(point(startX - 250, startY))
                    .waitAction(waitOptions(ofMillis(1000)))
                    .moveTo(point(startX, startY))
                    .release().perform();
        }


        String bookName = secondBook.getText();
        LOGGER.info(bookName);


    }
}
