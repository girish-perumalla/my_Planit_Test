package com.planit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingTest {
	static WebDriver driver;// This is global variable for webDriver interface.
	static WebDriverWait w;// This is the globsl variable for WebDriverWait

	public static void initiateDriver() {
		// This method will initiate the driver and opens chrome browser
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\perumallag\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	public static void launchURL() {
		// This method will launch the URl and maximize the window,delete cookies and
		// wait until the page is fully loaded.
		driver.get("http://demowebshop.tricentis.com/");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		w = new WebDriverWait(driver, 20);
	}

/*	public  void openExcel() throws IOException {
		// This method will open the sheet from the excel.
		File src = new File("D:\\Testdata.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet Sheet = wb.getSheet("Sheet1");
	}*/

	public static void clickOnLogin() {
		// This method will click on the login button
		WebElement login = driver.findElement(By.xpath("//a[text()='Log in']"));
		w.until(ExpectedConditions.elementToBeClickable(login));
		login.click();
	}

	public static void validateTitle() {
		// This method will validate the message
		String exp_Title = "Welcome, Please Sign In!";
		WebElement msg = driver.findElement(By.xpath("//h1[text()='Welcome, Please Sign In!']"));
		String text = msg.getText();
		Assert.assertEquals(text, exp_Title);
	}

	public static void doLogin(String email,String passwordvalue) throws IOException {
		// This method will complete the login process for this website

		WebElement username = driver.findElement(By.name("Email"));
		w.until(ExpectedConditions.visibilityOf(username));
		username.sendKeys(email);
		WebElement password = driver.findElement(By.name("Password"));
		w.until(ExpectedConditions.visibilityOf(password));
		password.sendKeys(passwordvalue);

		WebElement login_button = driver.findElement(By.xpath("(//input[@type='submit'])[2]"));
		w.until(ExpectedConditions.elementToBeClickable(login_button));
		login_button.click();

	}

	public static void validateAcountID() {
		// This method will validate user account ID
		String exp_Title1 = "atest@gmail.com";
		WebElement msg1 = driver.findElement(By.xpath("(//div[@class='header-links']/ul/li)[1]"));
		String text1 = msg1.getText();
		Assert.assertEquals(text1, exp_Title1);
	}

	public static void clearCart() {
		// This method will clear cart if any and click on books from menu.
		WebElement cart = driver.findElement(By.xpath("//div[@class='header-links']/ul/li[3]"));
		w.until(ExpectedConditions.elementToBeClickable(cart));
		cart.click();
		if (driver.findElement(By.xpath("//div[@class='order-summary-content']")).isDisplayed()) {
			// click books
			WebElement books = driver.findElement(By.xpath("//ul[@class='top-menu']/li/a[contains(text(),'Books')]"));
			w.until(ExpectedConditions.elementToBeClickable(books));
			books.click();
		}
		else {

			// for remove in cart
			WebElement remove_checkbox = driver.findElement(By.xpath("//input[@name='removefromcart']"));
			w.until(ExpectedConditions.elementToBeClickable(remove_checkbox));
			remove_checkbox.click();
			WebElement update_cart_button = driver.findElement(By.xpath("//input[@name='updatecart']"));
			w.until(ExpectedConditions.elementToBeClickable(update_cart_button));
			update_cart_button.click();
		}
	}

	public static void getPriceQuantity(String quantity) {
		// This method will click first book to get price and quantity
		WebElement first_book = driver.findElement(By.xpath("(//div[@class='product-item'])[1]"));
		w.until(ExpectedConditions.elementToBeClickable(first_book));
		first_book.click();

		// get price text
		String a = driver.findElement(By.xpath("//label[text()='Price:']/following-sibling::span")).getText();
		System.out.println(a);
		// enter quantity
		WebElement quan = driver.findElement(By.xpath("//input[@id='addtocart_13_EnteredQuantity']"));
		w.until(ExpectedConditions.visibilityOf(quan));
		quan.clear();
		quan.sendKeys(quantity);
	}

	public void clickCart() throws InterruptedException {
		// This method will click on Add to cart
		WebElement Add_to_cart = driver.findElement(By.xpath("//input[@id='add-to-cart-button-13']"));
		w.until(ExpectedConditions.elementToBeClickable(Add_to_cart));
		//Actions a = new Actions(driver);
		//a.moveToElement(Add_to_cart).doubleClick().build().perform();
		Add_to_cart.click();
		Thread.sleep(2000);
	}

	public static void validateCartTitle() {
		// This method will validate cart title
		String exp_Title2 = "The product has been added to your shopping cart";
		WebElement msg2 = driver.findElement(By.xpath("//*[@class='content']"));
		String text2 = msg2.getText();
		Assert.assertEquals(text2, exp_Title2);
	}

	public static void clickShoppingCartLink() {
		// This method will click on shopping cart in the top right
		WebElement shopping_cart = driver.findElement(By.xpath("(//div[@class='header-links']/ul/li)[3]"));
		w.until(ExpectedConditions.elementToBeClickable(shopping_cart));
		shopping_cart.click();
	}

	public static void validateSubTotal() {
		// This method will validate subtotal price
		String sub_Total3 = "20.00";//I am adding 2 items,so my expected price for two items in 20.00
		WebElement msg3 = driver.findElement(By.xpath("(//span[@class='nobr'])[2]/span"));
		String text3 = msg3.getText();
		Assert.assertEquals(text3, sub_Total3);
	}

	public static void acceptTerms() {
		// This method will click on accept terms checkbox
		WebElement terms = driver.findElement(By.xpath("//div[@class='terms-of-service']/input[@id='termsofservice']"));
		w.until(ExpectedConditions.elementToBeClickable(terms));
		terms.click();

	}

	public static void clickCheckOut() throws InterruptedException {
		// This method will click on check out button.

		WebElement checkout = driver.findElement(By.xpath("//button[@id='checkout']"));
		w.until(ExpectedConditions.elementToBeClickable(checkout));
		checkout.click();
		Thread.sleep(2000);
	}

	public static void newBillingAddress(ShoppingData shoppingData) throws InterruptedException {
		// This method will select new address in billing address dropdown and click
		// continue
		WebElement add = driver.findElement(By.id("billing-address-select"));
		w.until(ExpectedConditions.visibilityOf(add));
		Select s = new Select(add);
		s.selectByVisibleText(shoppingData.getNewaddress());

		// fill first name
		WebElement first_name = driver.findElement(By.id("BillingNewAddress_FirstName"));
		w.until(ExpectedConditions.visibilityOf(first_name));
		first_name.clear();
		first_name.sendKeys(shoppingData.getFirstname());
		// last name
		WebElement last_name = driver.findElement(By.id("BillingNewAddress_LastName"));
		w.until(ExpectedConditions.visibilityOf(last_name));
		last_name.clear();
		last_name.sendKeys(shoppingData.getLastname());

		// country dropdown
		WebElement country = driver.findElement(By.id("BillingNewAddress_CountryId"));
		w.until(ExpectedConditions.visibilityOf(country));
		Select s1 = new Select(country);
		s1.selectByVisibleText(shoppingData.getCountry());
		// city
		WebElement city = driver.findElement(By.id("BillingNewAddress_City"));
		w.until(ExpectedConditions.visibilityOf(city));

		city.sendKeys(shoppingData.getCity());

		// Address-1
		WebElement address_1 = driver.findElement(By.id("BillingNewAddress_Address1"));
		w.until(ExpectedConditions.visibilityOf(address_1));

		address_1.sendKeys(shoppingData.getAddress_1());

		// postal code
		WebElement postal_code = driver.findElement(By.id("BillingNewAddress_ZipPostalCode"));
		w.until(ExpectedConditions.visibilityOf(postal_code));

		postal_code.sendKeys(shoppingData.getPostacode());

		// phone number
		WebElement phone = driver.findElement(By.id("BillingNewAddress_PhoneNumber"));
		w.until(ExpectedConditions.visibilityOf(phone));
		phone.sendKeys(shoppingData.getPhone());

		// click on continue
		WebElement bill_continue = driver
				.findElement(By.xpath("//div[@id='billing-buttons-container']/input[@type='button']"));
		w.until(ExpectedConditions.elementToBeClickable(bill_continue));
		bill_continue.click();
		Thread.sleep(3000);
	}

	public static void shippingAddress() throws InterruptedException {
		// This method will select same billing address from shipping address dropdown
		// and click continue.
		WebElement shipping_add = driver.findElement(By.id("shipping-address-select"));
		w.until(ExpectedConditions.visibilityOf(shipping_add));
		Select s2 = new Select(shipping_add);
		s2.selectByVisibleText("abcd efgh, kukatpally, Hyderabad 500081, India ");

		// click on continue
		WebElement ship_continue = driver
				.findElement(By.xpath("(//input[@class='button-1 new-address-next-step-button'])[2]"));
		w.until(ExpectedConditions.elementToBeClickable(ship_continue));
		ship_continue.click();
		Thread.sleep(3000);
	}

	public static void shippingMethod() throws InterruptedException {
		// This method will click shipping method radio button and click on continue.
		WebElement ship_method = driver.findElement(By.xpath("//div[@class='section shipping-method']/ul/li[2]/div"));
		w.until(ExpectedConditions.elementToBeClickable(ship_method));
		ship_method.click();
		Thread.sleep(3000);

		// click on continue
		WebElement shipmet_continue = driver
				.findElement(By.xpath("//input[@class='button-1 shipping-method-next-step-button']"));
		w.until(ExpectedConditions.elementToBeClickable(shipmet_continue));
		shipmet_continue.click();
		Thread.sleep(3000);
	}

	public static void cod() throws InterruptedException {
		// This method will click on COD option and click continue

		WebElement COD = driver.findElement(By.xpath("//label[text()='Cash On Delivery (COD) (7.00)']"));
		w.until(ExpectedConditions.elementToBeClickable(COD));
		COD.click();
		Thread.sleep(3000);

		// click on continue
		WebElement COD_continue = driver
				.findElement(By.xpath("//input[@class='button-1 payment-method-next-step-button']"));
		w.until(ExpectedConditions.elementToBeClickable(COD_continue));
		COD_continue.click();
		Thread.sleep(3000);
	}

	public static void paymentInfo() throws InterruptedException {
		// This method will click on payment info.
		WebElement payment_info_continue = driver
				.findElement(By.xpath("//input[@class='button-1 payment-info-next-step-button']"));
		w.until(ExpectedConditions.elementToBeClickable(payment_info_continue));
		payment_info_continue.click();
		Thread.sleep(3000);
	}

	public static void confirmOrder() throws InterruptedException {
		// This method will click on confirm
		WebElement confirm = driver.findElement(By.xpath("//input[@class='button-1 confirm-order-next-step-button']"));
		w.until(ExpectedConditions.elementToBeClickable(confirm));
		confirm.click();
		Thread.sleep(3000);
	}

	public static void validationOrder() {
		// This method will verify order placed validation
		String order_confirm = "Your order has been successfully processed!";
		WebElement msg4 = driver.findElement(By.xpath("//ul[@class='details']/preceding-sibling::div"));
		String text4 = msg4.getText();
		Assert.assertEquals(text4, order_confirm);
	}

	public static void lastContinue() {
		// This method will click last continue
		WebElement last_continue = driver
				.findElement(By.xpath("//input[@class='button-2 order-completed-continue-button']"));
		w.until(ExpectedConditions.elementToBeClickable(last_continue));
		last_continue.click();
	}

	public static void logout() {
		// This method will click logout
		WebElement logout = driver.findElement(By.xpath("//a[text()='Log out']"));
		w.until(ExpectedConditions.elementToBeClickable(logout));
		logout.click();
	}

	public static void closeApp() {
		// This method will close the application.
		// wb.close();
		driver.close();
	}
	
	

	@Test(dataProvider ="shoppingcart" , dataProviderClass =ReadExcelData.class)
	public void addToShopingCart(ShoppingData shoppingData) throws InterruptedException, IOException {
		// In the main(),calling all above created static methods directly
		//openExcel();
		initiateDriver();
		launchURL();
		clickOnLogin();
		validateTitle();
		doLogin(shoppingData.getEmail(),shoppingData.getPassword());
		validateAcountID();
		clearCart();
		getPriceQuantity(shoppingData.getQuantity());
		validateCartTitle();
		clickShoppingCartLink();
		validateSubTotal();
		acceptTerms();
		clickCheckOut();
		newBillingAddress(shoppingData);
		shippingAddress();
		shippingMethod();
		cod();
		paymentInfo();
		confirmOrder();
		validationOrder();
		lastContinue();
		logout();
		closeApp();

	}// main() close
}// class close
