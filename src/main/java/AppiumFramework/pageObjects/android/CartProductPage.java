package AppiumFramework.pageObjects.android;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import AppiumFramework.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartProductPage extends AndroidActions { // Renamed class to PascalCase
    public CartProductPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termsButton;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement acceptButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedButton;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement checkBox;
    
    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement cartIcon;
    
    @AndroidFindBy(id = "com.androidsample.generalstore:id/webView")
    private WebElement webView;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/alertTitle']")
    private WebElement alertTitle;
    
    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_back")
    private WebElement appBackBtn;
    
    
    

    public List<WebElement> getProductList() {
        return productList;
    }

    public double calculateTotalProductSum() {
        double totalSum = 0;
        for (WebElement product : productList) {
            String amountString = waitForElement(product, 5).getText();
            totalSum += getFormattedAmount(amountString);
        }
        return totalSum;
    }

    public double getTotalAmountDisplayed() {
        return getFormattedAmount(waitForElement(totalAmount, 5).getText());
    }

    public void acceptTermsAndConditions() {
        longPressAction(waitForElement(termsButton, 5));
        acceptButton.click();
    }

    public void submitOrder() {
        waitForElement(checkBox, 5).click();
        waitForElement(proceedButton, 5).click();
    }
    
    public boolean isCartIconDisplayed() {
    	waitForElement(cartIcon,5);
    	return cartIcon.isDisplayed();
    }
    
    public boolean isWebviewDisplayed() {
    	waitForElement(webView, 5);
    	return webView.isDisplayed();
    	
    }
    
    public void TnC() {
    	longPressAction(waitForElement(termsButton, 5));
    }
    
    public String isAltertTitleDisplayed() {
    	waitForElement(alertTitle, 5);
    	return alertTitle.getText();
    }

	public void clickOnBackButton() {
		waitForElement(appBackBtn, 5);
		appBackBtn.click();
	}
    
}
