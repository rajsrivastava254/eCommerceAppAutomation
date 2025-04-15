package utils;

import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;

public class TestListener implements ITestListener {

    private AndroidDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getMethod().getMethodName());
        saveTestLog("Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getMethod().getMethodName());
        saveFailureLog(result.getThrowable().getMessage());
        
        try {
            driver = (AndroidDriver) result.getTestClass().getRealClass()
                    .getDeclaredField("driver")
                    .get(result.getInstance());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Error retrieving driver instance: " + e.getMessage());
            e.printStackTrace();
        }

        if (driver != null) {
            saveScreenshot(driver);
        } else {
            System.out.println("Driver is null, screenshot not captured");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getMethod().getMethodName());
        saveTestLog("Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
    }

    @Attachment(value = "Failure Log", type = "text/plain")
    private String saveFailureLog(String message) {
        return message;
    }

    @Attachment(value = "Test Log", type = "text/plain")
    private String saveTestLog(String message) {
        return message;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] saveScreenshot(AndroidDriver driver) {
        return driver.getScreenshotAs(OutputType.BYTES);
    }
}