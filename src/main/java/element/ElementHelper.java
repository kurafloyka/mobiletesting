package element;


import org.openqa.selenium.By;

public class ElementHelper {


    public static By getElementInfoToBy(ElementInfo elementInfo) {
        By by = null;
        if (elementInfo.getType().equals("xpath")) {
            by = By.xpath(elementInfo.getValue());
        } else if (elementInfo.getType().equals("id")) {
            by = By.id(elementInfo.getValue());
        } else if(elementInfo.getType().equals("name")){
            by=By.name(elementInfo.getValue());
        }
        return by;
    }


}