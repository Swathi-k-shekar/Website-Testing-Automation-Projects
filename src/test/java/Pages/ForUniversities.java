package Pages;

import java.time.Duration;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ForUniversities {
	WebDriver driver;
	WebDriverWait wait;

	public ForUniversities(WebDriver driver) {
		 this.driver = driver;
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h2[contains(text(),'Get in touch with our')]")
	WebElement formHead;
	
	@FindBy(xpath="//input[@id='FirstName']")
	WebElement nameFirst;

	@FindBy(xpath="//input[@id='LastName']")
	WebElement nameLast;
	
	@FindBy(xpath="//input[@id='Email']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='Phone']")
	WebElement phoneNumb;
	
	@FindBy(xpath="//select[@id='Institution_Type__c']")
	WebElement insType;
	
	@FindBy(xpath="//input[@id='Company']")
	WebElement insName;
	
	@FindBy(xpath="//select[@id='Title']")
	WebElement jobRole;
	
	@FindBy(xpath="//select[@id='Department']")
	WebElement departmt;
	
//	@FindBy(xpath="//input[@id='FirstName']")
//	WebElement checkBox;
	
	@FindBy(xpath="//select[@id='Country']")
	WebElement country;
	
	@FindBy(xpath="//select[@id='What_the_lead_asked_for_on_the_website__c']")
	WebElement needs;
	
	@FindBy(className="mktoButton")
	WebElement submitbtn;
	
	@FindBy(id="ValidMsgEmail")
	WebElement msg;
	
	public void formValidation() {
		Assert.assertTrue(formHead.isDisplayed(), "Form is not displayed on the webpage.");
	}

	public void formfeildsValidation() {
		//wait.until(ExpectedConditions.visibilityOf(formHead));
		Assert.assertTrue(nameFirst.isDisplayed(), "Form is not displayed on the webpage.");
		Assert.assertTrue(nameLast.isDisplayed(), "Form is not displayed on the webpage.");
		Assert.assertTrue(email.isDisplayed(), "Form is not displayed on the webpage.");
		Assert.assertTrue(phoneNumb.isDisplayed(), "Form is not displayed on the webpage.");
		Assert.assertTrue(insType.isDisplayed(), "Form is not displayed on the webpage.");
		Assert.assertTrue(insName.isDisplayed(), "Form is not displayed on the webpage.");
		Assert.assertTrue(jobRole.isDisplayed(), "Form is not displayed on the webpage.");
		Assert.assertTrue(departmt.isDisplayed(), "Form is not displayed on the webpage.");
		Assert.assertTrue(country.isDisplayed(), "Form is not displayed on the webpage.");
		Assert.assertTrue(needs.isDisplayed(), "Form is not displayed on the webpage.");
	}
	
	public void formFill(String first, String last, String phone, String mail, String instType, String insname, String role, String cntry, String dept,String need) {
		nameFirst.sendKeys(first);
		nameLast.sendKeys(last);
		email.sendKeys(mail);
		phoneNumb.sendKeys(phone);
		insType.sendKeys(instType);
		insName.sendKeys(insname);
		jobRole.sendKeys(role);
		country.sendKeys(cntry);
		departmt.sendKeys(dept);
		needs.sendKeys(need);
	}
	
	public void formSubmit() {
		submitbtn.click();
	}
	
	public String errorCapture() {
		String alertMessage = msg.getText();
		System.err.println("Error Message: " + alertMessage);
		
        return alertMessage;
	}

}
