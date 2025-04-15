package AppiumFramework.pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import AppiumFramework.utils.AndroidActions;

public class FormPage extends AndroidActions {
    
    public FormPage(AndroidDriver driver) {
        super(driver); // Calls AndroidActions constructor
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Using @AndroidFindBy for element management
    
    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement appTitle;
    
    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']") 
    private WebElement femaleOption;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']") 
    private WebElement maleOption;

    @AndroidFindBy(id = "android:id/text1")
    private WebElement selectCountry;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement SubmitButton;
    
    @AndroidFindBy(xpath = "(//android.widget.Toast)[1]") 
    private WebElement toastMessage;


    // ✅ Now using waitForElement from AndroidActions instead of creating a new WebDriverWait
    public void setNameField(String name) {
        waitForElement(nameField, 10).sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender) {
        if (gender.equalsIgnoreCase("Female")) {
            femaleOption.click();
        } else {
            maleOption.click();
        }
    }

    public void setCountrySelection(String countryName) {
        selectCountry.click();
        scrollToText(countryName);
        WebElement country = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + countryName + "']"));
        country.click();
    }

    public ProductCataloguePage submitForm() {
        SubmitButton.click();
        return new ProductCataloguePage(driver);
    }
    
    public String nameToast() {
    	return toastMessage.getText();
    }
    
    public String getAppTitle() {
    	return appTitle.getText();
    }
    
    public Boolean submitButtonEnable() {
    	waitForElement(SubmitButton, 5);
    	return SubmitButton.isEnabled();
    }
}
