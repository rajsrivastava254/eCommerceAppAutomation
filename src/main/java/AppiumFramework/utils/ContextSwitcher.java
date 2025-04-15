package AppiumFramework.utils;

import io.appium.java_client.android.AndroidDriver;

import java.util.Set;

public class ContextSwitcher {

    private AndroidDriver driver;

    public ContextSwitcher(AndroidDriver driver) {
        this.driver = driver;
    }

    public void switchToWebView(String webViewContext) throws InterruptedException {
        Set<String> contexts = driver.getContextHandles();
        for (String contextName : contexts) {
            System.out.println(contextName);
        }
        Thread.sleep(3000);
        driver.context(webViewContext);
    }

    public void switchToNativeApp() {
        driver.context("NATIVE_APP");
    }
}
