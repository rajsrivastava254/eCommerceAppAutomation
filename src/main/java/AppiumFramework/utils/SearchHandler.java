package AppiumFramework.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class SearchHandler {

    private AndroidDriver driver;
    private ContextSwitcher contextSwitcher;

    public SearchHandler(AndroidDriver driver) {
        this.driver = driver;
        this.contextSwitcher = new ContextSwitcher(driver);
    }

    public void searchInWebView(String webViewContext, String searchText) throws InterruptedException {
        contextSwitcher.switchToWebView(webViewContext);

        driver.findElement(By.name("q")).sendKeys(searchText);
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        contextSwitcher.switchToNativeApp();
    }
}
