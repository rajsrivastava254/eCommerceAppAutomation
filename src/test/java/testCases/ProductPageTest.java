package testCases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppiumFramework.utils.BaseCaseFrame;
import utils.Constants;
import utils.DataProviders;
import utils.JsonReaderUtil;

public class ProductPageTest extends BaseCaseFrame {

	String toolBarTitle = (String) JsonReaderUtil.getAnyKeyValue(Constants.TESTDATA_JSON_FILE_PATH, "title");
	String generalStoreTitle = (String) JsonReaderUtil.getAnyKeyValue(Constants.TESTDATA_JSON_FILE_PATH, "store");
	String toastMessage = (String) JsonReaderUtil.getAnyKeyValue(Constants.TESTDATA_JSON_FILE_PATH, "toast");

	@Test(groups = { "smoke" }, dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void isProductCostVisible(HashMap<String, String> input) throws InterruptedException {
		loginIntoProductPage(input);
		Assert.assertEquals(productCatalogue.isProductPriceDisplayed(), true);
	}

	@Test(groups = { "smoke" }, dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void isProductNameVisible(HashMap<String, String> input) throws InterruptedException {
		loginIntoProductPage(input);
		Assert.assertEquals(productCatalogue.isProductNameDisplayed(), true);
	}

	@Test(groups = { "smoke" }, dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void isProductIconVisible(HashMap<String, String> input) throws InterruptedException {
		loginIntoProductPage(input);
		Assert.assertEquals(productCatalogue.isProductIconDisplayed(), toolBarTitle);
	}

	@Test(groups = { "smoke" }, dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void isProductVisibleInCart(HashMap<String, String> input) throws InterruptedException {
		loginIntoProductPage(input);
		productCatalogue.addProductToCart(0);
		productCatalogue.goToCart();
		Assert.assertEquals(productCatalogue.isProductNameDisplayed(), true);
	}

	@Test(groups = { "smoke" }, dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void scrollAndAddProductIntoCart(HashMap<String, String> input) throws InterruptedException {
		loginIntoProductPage(input);
		productCatalogue.scrollToProduct(input.get("product"));
		productCatalogue.addProductToCart(1);
		productCatalogue.goToCart();
		Assert.assertEquals(productCatalogue.isProductNameDisplayed(), true);
	}

	@Test(groups = { "smoke" }, dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void backButtonEnable(HashMap<String, String> input) throws InterruptedException {
		loginIntoProductPage(input);
		productCatalogue.clickOnBackButton();
		Assert.assertEquals(formPage.getAppTitle(), generalStoreTitle);
	}

	@Test(groups = { "smoke" }, dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void goToCartWithoutAddingProducts(HashMap<String, String> input) throws InterruptedException {
		loginIntoProductPage(input);
		productCatalogue.goToCart();
		Assert.assertEquals(productCatalogue.isToastMessageDisplayed(), toastMessage);
	}

	@Test(groups = { "smoke" }, dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void counterTextValidation(HashMap<String, String> input) throws InterruptedException {
		loginIntoProductPage(input);
		productCatalogue.addProductToCart(0);
		Assert.assertEquals(productCatalogue.isCounterTextDisplayed(), true);
	}

}