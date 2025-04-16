package AppiumFramework.utils;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions {
    protected AndroidDriver driver;
    WebDriverWait wait;

    public AndroidActions(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement waitForElement(WebElement element, int timeout) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return customWait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public void longPressAction(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 3000));
    }

    public void scrollToText(String text) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
    }

    public void SwipeAction(WebElement FirstId, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) FirstId).getId(), "duration", 3000, "direction", direction, "percent", 0.75));
    }

    public void DragDropAction(WebElement Drag, int endX, int endY) {
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) Drag).getId(), "endX", endX, "endY", endY));
    }

    public double getFormattedAmount(String amountString) {
        amountString = amountString.replace("$", "");
        return Double.parseDouble(amountString);
    }
}
