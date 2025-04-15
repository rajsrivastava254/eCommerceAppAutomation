package AppiumFramework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import AppiumFramework.pageObjects.android.CartProductPage;
import AppiumFramework.pageObjects.android.FormPage;
import AppiumFramework.pageObjects.android.ProductCataloguePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseCaseFrame {

	protected FormPage formPage;
	protected ProductCataloguePage productCatalogue;
	protected CartProductPage cartPage;
	protected SearchHandler searchHandler;

	public AppiumDriverLocalService service;
	public WebDriverWait wait;
	public AndroidDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void ConfigureAppium() throws URISyntaxException, IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//resources//data.properties");
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");

		service = AppiumDriverLocalService.buildDefaultService();

		service.start();
		// Appium Code --> AppiumServer --> Mobile
		UiAutomator2Options options = new UiAutomator2Options();

		options.setDeviceName(prop.getProperty("AndroidDevice"));
		options.setChromedriverExecutable(
				"C://Users//User1//Downloads//chromedriver-win64//chromedriver-win64//chromedriver.exe");
		options.setApp(System.getProperty("user.dir") + "//src//main//resources//General-Store.apk");
		driver = new AndroidDriver(new URI("http://" + ipAddress + ":" + port).toURL(), options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		formPage = new FormPage(driver);
		productCatalogue = new ProductCataloguePage(driver);
		cartPage = new CartProductPage(driver);
		searchHandler = new SearchHandler(driver);

	}

	@AfterMethod(alwaysRun = true)
	public void TearDownMethod() {
		driver.quit();
		service.stop();
	}

	public void loginIntoProductPage(HashMap<String, String> input) {
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("genderMale"));
		formPage.setCountrySelection(input.get("country"));
		formPage.submitForm();
	}

	public void loginTillCartPage(HashMap<String, String> input) {
		loginIntoProductPage(input);
		productCatalogue.addProductToCart(0);
		productCatalogue.goToCart();
	}

}
