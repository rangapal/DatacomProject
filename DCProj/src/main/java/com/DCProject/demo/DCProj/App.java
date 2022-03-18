package com.DCProject.demo.DCProj;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import net.bytebuddy.implementation.ToStringMethod;

public class App 
{
	public static void main( String[] args )
	{
		String baseUrl = "https://www.demo.bnz.co.nz/client/";


		//TC1(baseUrl);
		//TC2(baseUrl);
		//TC3(baseUrl);
		//TC4(baseUrl);
		TC5(baseUrl);

	}



	//Navigate to payees page
	private static void navigateToPayees(WebDriver driver) {

		//Click Menu and Payees buttons
		WebElement menuButton = driver.findElement(By.xpath("//*[@id=\"left\"]/div[1]/div/button"));

		if (menuButton.isDisplayed()) {
			menuButton.click();
		}

		WebElement payeesButton = driver.findElement(By.xpath("//*[@id=\"left\"]/div[1]/div/div[3]/section/div[2]/nav[1]/ul/li[3]/a"));

		if (payeesButton.isDisplayed()) {
			payeesButton.click();
		}

	}

	private static void addPayee(WebDriver driver) {


		//Payee variables
		String payeeName = "Ranga Palakonda";
		CharSequence accountNumber = "12-1234-1234567-00";


		//Wait until add buttons is displayed then click add
		do {
			holdExecution(2000);

		} while (!driver.findElement(By.xpath("//*[@id=\"YouMoney\"]/div/div/div[3]/section/section/div/div[2]/header[2]/div/div[3]/button")).isDisplayed()) ;

		driver.findElement(By.xpath("//*[@id=\"YouMoney\"]/div/div/div[3]/section/section/div/div[2]/header[2]/div/div[3]/button")).click();
		holdExecution(2000);

		//Enter payee name and split payee acc number into parts and enter into input fields
		WebElement payeeNameInput = driver.findElement(By.xpath("//*[@id=\"ComboboxInput-apm-name\"]"));

		if (payeeNameInput.isDisplayed()) {
			payeeNameInput.sendKeys(payeeName);
			driver.findElement(By.xpath("//*[@id=\"combobox-3508_-99\"]")).click();

			CharSequence bank = accountNumber.subSequence(0, 2);
			CharSequence branch = accountNumber.subSequence(3, 7);
			CharSequence account = accountNumber.subSequence(8, 15);
			CharSequence suffix = accountNumber.subSequence(16, 18);


			driver.findElement(By.xpath("//*[@id=\"apm-bank\"]")).sendKeys(bank);
			driver.findElement(By.xpath("//*[@id=\"apm-branch\"]")).sendKeys(branch);
			driver.findElement(By.xpath("//*[@id=\"apm-account\"]")).sendKeys(account);
			driver.findElement(By.xpath("//*[@id=\"apm-suffix\"]")).sendKeys(suffix);


		}

		//Click add button on add payee dialog window
		driver.findElement(By.xpath("//*[@id=\"apm-form\"]/div[2]/button[3]")).click();

		holdExecution(3000);

		//Check that new payee is added to the list of payees
		if (driver.findElement(By.xpath("//*[contains(text(), '"+payeeName+"')]")).isDisplayed()) {
			System.out.println("Successfully added payee");

		} 

	}





	//Navigates to payees window and adds payee
	private static void TC1(String baseUrl) {

		// Initialize
		WebDriver driver = new ChromeDriver();

		driver.get(baseUrl);
		driver.manage().deleteAllCookies();
		holdExecution(3000);

		navigateToPayees(driver);

		//Check that page heading displays 'Payees'
		if (driver.findElement(By.xpath("//*[@id=\"YouMoney\"]/div/div/div[3]/section/header/h1/span")).getText().equals("Payees")) {
			System.out.println("Payees page loaded successfully");
		}


		holdExecution(3000);

		driver.close();

	}







	private static void TC2(String baseUrl) {

		//Initialize
		WebDriver driver = new ChromeDriver();

		driver.get(baseUrl);
		driver.manage().deleteAllCookies();
		holdExecution(3000);


		navigateToPayees(driver);
		addPayee(driver);


		driver.close();

	}



	private static void TC3(String baseUrl) {
		//Initialize
		WebDriver driver = new ChromeDriver();

		driver.get(baseUrl);
		driver.manage().deleteAllCookies();
		holdExecution(3000);

		navigateToPayees(driver);

		By addPayeeButton = By.xpath("//*[@id=\"YouMoney\"]/div/div/div[3]/section/section/div/div[2]/header[2]/div/div[3]/button");
		By addPayeeDialogButton = By.xpath("//*[@id=\"apm-form\"]/div[2]/button[3]");
		By errorMessage = By.xpath("//*[contains(text(), 'A problem was found. Please correct the field highlighted below.')]");

		//Payee variables
		String payeeName = "Ranga Palakonda";
		CharSequence accountNumber = "12-1234-1234567-00";

		//Wait until add buttons is displayed then click add
		do {
			holdExecution(2000);

		} while (!driver.findElement(addPayeeButton).isDisplayed()) ;

		driver.findElement(addPayeeButton).click();
		holdExecution(2000);

		//Click add button before filling any fields
		driver.findElement(addPayeeDialogButton).click();


		//Check that an error is shown, if error shown, fill in the fields
		if (driver.findElement(errorMessage).isDisplayed()) {
			System.out.println("Error message displayed");

			//Enter payee name and split payee acc number into parts and enter into input fields
			WebElement payeeNameInput = driver.findElement(By.xpath("//*[@id=\"ComboboxInput-apm-name\"]"));

			if (payeeNameInput.isDisplayed()) {
				payeeNameInput.sendKeys(payeeName);
				driver.findElement(By.xpath("//*[@id=\"combobox-3508_-99\"]")).click();

				CharSequence bank = accountNumber.subSequence(0, 2);
				CharSequence branch = accountNumber.subSequence(3, 7);
				CharSequence account = accountNumber.subSequence(8, 15);
				CharSequence suffix = accountNumber.subSequence(16, 18);


				driver.findElement(By.xpath("//*[@id=\"apm-bank\"]")).sendKeys(bank);
				driver.findElement(By.xpath("//*[@id=\"apm-branch\"]")).sendKeys(branch);
				driver.findElement(By.xpath("//*[@id=\"apm-account\"]")).sendKeys(account);
				driver.findElement(By.xpath("//*[@id=\"apm-suffix\"]")).sendKeys(suffix);


			}


		}

		//Click add button on add payee dialog window
		driver.findElement(addPayeeDialogButton).click();

		holdExecution(3000);

		//Check that no error message present on screen
		if(driver.findElements(errorMessage).isEmpty()) {
			System.out.println("Error message no longer displayed");
		} else {
			System.out.println("Error message still displayed");
		}

		driver.close();

	}


	private static void TC4(String baseUrl) {
		WebDriver driver = new ChromeDriver();

		driver.get(baseUrl);
		driver.manage().deleteAllCookies();
		holdExecution(3000);

		navigateToPayees(driver);
		addPayee(driver);


		//WIP Check sort order triangle

		By sortTriangle = By.xpath("//*[@id=\"YouMoney\"]/div/div/div[3]/section/section/div/div[2]/header[2]/div/div[1]/h3/svg[contains(text(), 'Icon IconChevronDownSolid')]");

		//Check that asc sort order icon is shown
		if(driver.findElements(sortTriangle).isEmpty()) {
			System.out.println("Names sorted DESC");
			System.out.println((driver.findElements(sortTriangle)));
		} else {
			System.out.println("Names sorted ASC");
		}

	}






	private static void TC5(String baseUrl) {
		//Initialize
		WebDriver driver = new ChromeDriver();

		driver.get(baseUrl);
		driver.manage().deleteAllCookies();
		holdExecution(3000);
		
		
		double transferAmount = 500;
		
		//Web Element locators
		By paymentsButton = By.xpath("//*[contains(text(), 'Pay or transfer')]");
		By chooseFromAccount = By.xpath("//*[contains(text(), 'Choose account')]");
		By chooseToAccount = By.xpath("//*[contains(text(), 'Choose account, payee, or someone new')]");
		By everydayAccount = By.xpath("/html/body/div[8]/div/div/div[2]/div/div/ul/li[2]/button/div/div/div[2]/p[1]");
		By billsAccount = By.xpath("//*[@id=\"react-tabs-3\"]/ul/li[1]/button/div/div/div[2]/p[1]");
		By accountsTab = By.xpath("//*[@id=\"react-tabs-2\"]");
		By amountInput = By.xpath("//input[@name='amount']");
		By transferButton = By.xpath("//*[@id=\"paymentForm\"]/div[4]/div/button[1]");
		By billsAccountBalace = By.xpath("//*[@id=\"account-ACC-5\"]/div[2]/span[3]");
		
		//Current Bills account balance
		double billsAccBal = Double.parseDouble(driver.findElement(billsAccountBalace).getText());
		//Expected bills account balace
		double expectedAmount = (billsAccBal+transferAmount);
		System.out.println("Initial Bills account balance: $"+driver.findElement(billsAccountBalace).getText());

		//Open menu, click transfer, select accounts and fill in amount
		WebElement menuButton = driver.findElement(By.xpath("//*[@id=\"left\"]/div[1]/div/button"));

		if (menuButton.isDisplayed()) {
			menuButton.click();
		}
		
		holdExecution(2000);
		driver.findElement(paymentsButton).click();
		holdExecution(2000);
		driver.findElement(chooseFromAccount).click();
		holdExecution(2000);
		driver.findElement(everydayAccount).click();
		holdExecution(2000);
		driver.findElement(chooseToAccount).click();
		holdExecution(2000);
		driver.findElement(accountsTab).click();
		holdExecution(2000);
		driver.findElement(billsAccount).click();
		
		holdExecution(2000);
		driver.findElement(amountInput).sendKeys(String.valueOf(transferAmount));
		holdExecution(2000);
		driver.findElement(transferButton).click();
		holdExecution(2000);
		
		
		//Gets current bills account value
		billsAccBal = Double.parseDouble(driver.findElement(billsAccountBalace).getText());
		
		//Verifies it matches the expected amount
		if (expectedAmount==billsAccBal){
			System.out.println("Bills account balance is now :$"+billsAccBal);
			
		}
		
		
		driver.close();
		

	}







	//Holds the execution of automation script for the desired number of milliseconds
	public static void holdExecution(int milli) {
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			System.out.println("Error holding execution");
		}

	}

}
