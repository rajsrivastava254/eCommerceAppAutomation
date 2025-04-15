package testCases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppiumFramework.utils.BaseCaseFrame;
import utils.Constants;
import utils.JsonReaderUtil;
import utils.DataProviders;

public class FormPageTest extends BaseCaseFrame {

	String googleSearch = (String) JsonReaderUtil.getAnyKeyValue(Constants.TESTDATA_JSON_FILE_PATH, "search");
	String toolBarTitle = (String) JsonReaderUtil.getAnyKeyValue(Constants.TESTDATA_JSON_FILE_PATH, "title");
	String nameToastMsg = (String) JsonReaderUtil.getAnyKeyValue(Constants.TESTDATA_JSON_FILE_PATH, "nameToast");
	String generalStoreTitle = (String) JsonReaderUtil.getAnyKeyValue(Constants.TESTDATA_JSON_FILE_PATH, "store");
	String generalStore = (String) JsonReaderUtil.getAnyKeyValue(Constants.TESTDATA_JSON_FILE_PATH, "generalStore");

	@Test(groups = { "smoke" }, dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void FillForm(HashMap<String, String> input) throws InterruptedException {
		loginIntoProductPage(input);

		productCatalogue.addProductToCart(0);
		productCatalogue.addProductToCart(0);
		productCatalogue.goToCart();

		cartPage.acceptTermsAndConditions();
		cartPage.submitOrder();

		searchHandler.searchInWebView(generalStore, googleSearch);
		Assert.assertEquals(formPage.getAppTitle(), generalStoreTitle);
	}

	@Test(groups = { "smoke" }, dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void emptyNameFillForm(HashMap<String, String> input) throws InterruptedException {
		formPage.setNameField("");
		formPage.setGender(input.get("genderMale"));
		formPage.setCountrySelection(input.get("country"));
		formPage.submitForm();
		Assert.assertEquals(formPage.nameToast(), nameToastMsg);

	}
	
	@Test(groups = { "smoke" }, dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void femaleGenderTest(HashMap<String, String> input) throws InterruptedException {
		formPage.setNameField(input.get("user"));
		formPage.setGender(input.get("genderFemale"));
		formPage.setCountrySelection(input.get("country"));
		formPage.submitForm();
		Assert.assertEquals(productCatalogue.isProductIconDisplayed(), toolBarTitle);

	}

	@Test(groups = { "smoke" })
	public void verifyAppTitle() {
		String titleName = formPage.getAppTitle();
		Assert.assertEquals(titleName, generalStoreTitle);
	}

	@Test(groups = { "smoke" })
	public void verifySubmitButtonEnable() {
		boolean button = formPage.submitButtonEnable();
		Assert.assertEquals(button, true);
	}

}
