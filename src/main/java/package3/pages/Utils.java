package package3.pages;

/**
 * Created by imusiievych on 3/4/15.
 */
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Utils extends PageObject{

    // to be used for getting absolute file path from relative
    public static String test(){
        return("I am using utils");
    }

    public static String getResourcePath(String relativePath) {
        return new Utils().getClass().getResource("/").getPath() + relativePath;
    }

    // wait for jQuery ajax event
    public static void waitForJQuery(WebDriver driver) {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                return (Boolean) js.executeScript("return $.active == 0");
            }
        });
    }

    // return unique timestamp
    static java.util.Date date= new java.util.Date();
    private static String timestamp = "empty";

    public static String get_timestamp() {
        if (timestamp.equals("empty")) {
            timestamp = String.valueOf(date.getTime());
        }
        return timestamp;
    }

    // mouse hover method
    public static void hover_mouse_over_element(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).click().build().perform();
    }

    // methods for re-searching elements in dom-model
    public static String relocateElement(List<WebElement> elements) {
        String info = "{";
        try { info += String.format("Type: {0}; ", elements.get(0).getTagName()); } catch (Exception e) { }
        try { info += String.format("Tag: {0}; ",  elements.get(0).getText()); } catch (Exception e) { }
        info += "}";
        return info;
    }

    public static String relocateElement(WebElement element) {
        String info = "{";
        try { info += String.format("Type: {0}; ", element.getTagName()); } catch (Exception e) { }
        try { info += String.format("Tag: {0}; ",  element.getText()); } catch (Exception e) { }
        info += "}";
        return info;
    }

    public static void waitUntilElementIsInvisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitUntilElementIsVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementIsVisible(WebDriver driver, List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static boolean isElementPresent(WebDriver driver, By locator) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(locator);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return list.size() != 0 && list.get(0).isDisplayed();
    }

    public static String getStatus(String url) throws IOException {
        URL link = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)link.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(3000);
        connection.connect();
        return Integer.toString(connection.getResponseCode());
    }

    // switch to other window by part of url
    public void switch_to_by_window_url(WebDriver driver, String prefix) {
        Set<String> handles = driver.getWindowHandles();
        for (String handle: handles) {
            driver.switchTo().window(handle);
            System.out.println("Window handle #" + handle);
            if (driver.getCurrentUrl().contains(prefix)) return;
        }
    }

    // switch to other window by part of window title
    public void switch_to_by_window_title(WebDriver driver, String prefix) {
        Set<String> handles = driver.getWindowHandles();
        for (String handle: handles) {
            driver.switchTo().window(handle);
            System.out.println("Window handle #" + handle);
            if (driver.getTitle().contains(prefix)) return;
        }
    }

    // switch to iFrame by part of window title
    public void switch_to_iFrame(WebDriver driver, String prefix) {
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        if (driver.getTitle().contains(prefix)) return;

    }

}

