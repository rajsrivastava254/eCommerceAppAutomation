package AppiumFramework.pageObjects.android;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import AppiumFramework.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCataloguePage extends AndroidActions {

    public ProductCataloguePage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCartButtons;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartButton;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Products']")
    private WebElement productIcon;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice']")
    private WebElement productPrice;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']")
    private WebElement productName;
    
    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_back")
    private WebElement appBackButton;
    
    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Please add some product at first']")
    private WebElement toastMsg;
    
    @AndroidFindBy(id = "com.androidsample.generalstore:id/counterText")
    private WebElement counterText;
    
    

    public void addProductToCart(int index) {
        if (index >= 0 && index < addToCartButtons.size()) {
            waitForElement(addToCartButtons.get(index), 5).click();
        } else {
            System.out.println("Invalid product index: " + index);
        }
    }

    public CartProductPage goToCart() {
        waitForElement(cartButton, 5).click();
        return new CartProductPage(driver);
    }
    
    public boolean isProductPriceDisplayed() {
    	waitForElement(productPrice,5);
    	return productPrice.isDisplayed();
    }
    
    public boolean isProductNameDisplayed() {
    	waitForElement(productName,5);
    	return productName.isDisplayed();
    }
    
    public void scrollToProduct(String Product) {
    	scrollToText(Product);
    }
    
    public String isProductIconDisplayed() {
    	waitForElement(productIcon,5);
    	return productIcon.getText();
    }
    
    public void clickOnBackButton() {
    	waitForElement(appBackButton, 5);
    	appBackButton.click();
    }
    
    public String isToastMessageDisplayed() {
    	return toastMsg.getText();
    }
    
    public boolean isCounterTextDisplayed() {
    	return counterText.isDisplayed();
    }
}
