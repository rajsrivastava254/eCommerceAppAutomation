package testCases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppiumFramework.utils.BaseCaseFrame;
import utils.Constants;
import utils.DataProviders;
import utils.JsonReaderUtil;

public class CartPageTest extends BaseCaseFrame{
	
	String mismatched = (String) JsonReaderUtil.getAnyKeyValue(Constants.TESTDATA_JSON_FILE_PATH, "toastSum");
	String alert = (String) JsonReaderUtil.getAnyKeyValue(Constants.TESTDATA_JSON_FILE_PATH, "alertTitle");
	String toolBarTitle = (String) JsonReaderUtil.getAnyKeyValue(Constants.TESTDATA_JSON_FILE_PATH, "title");
	
	@Test(groups = {"smoke"},dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void isCartVisible(HashMap<String, String> input) throws InterruptedException {
		loginTillCartPage(input);
		Assert.assertEquals(cartPage.isCartIconDisplayed(), true);
	}
	
	@Test(groups = {"smoke"},dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void productPriceSumVerification(HashMap<String, String> input) throws InterruptedException {
		loginIntoProductPage(input);
		productCatalogue.addProductToCart(0);
		productCatalogue.addProductToCart(0);
		productCatalogue.goToCart();
		double totalSum = cartPage.calculateTotalProductSum();
	    double displayedFormattedSum = cartPage.getTotalAmountDisplayed();
	    Assert.assertEquals(totalSum, displayedFormattedSum, mismatched);
	}
	
	@Test(groups = {"smoke"},dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void productInCart(HashMap<String, String> input) throws InterruptedException {
		loginTillCartPage(input);
	    Assert.assertEquals(productCatalogue.isProductNameDisplayed(), true);
	}
	
	@Test(groups = {"smoke"},dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void webViewVerification(HashMap<String, String> input) throws InterruptedException {
		loginTillCartPage(input);
		cartPage.submitOrder();
	    Assert.assertEquals(cartPage.isWebviewDisplayed(), true);
	}
	
	@Test(groups = {"smoke"},dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void termsAndConditions(HashMap<String, String> input) throws InterruptedException {
		loginTillCartPage(input);
		cartPage.TnC();
	    Assert.assertEquals(cartPage.isAltertTitleDisplayed(), alert);
	}
	
	@Test(groups = {"smoke"},dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void backButtonVerification(HashMap<String, String> input) throws InterruptedException {
		loginTillCartPage(input);
		cartPage.clickOnBackButton();
	    Assert.assertEquals(productCatalogue.isProductIconDisplayed(), toolBarTitle);
	}
	
	@Test(groups = {"smoke"},dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void productAmountVerification(HashMap<String, String> input) throws InterruptedException {
		loginTillCartPage(input);
	    Assert.assertEquals(productCatalogue.isProductPriceDisplayed(), true);
	}

}
