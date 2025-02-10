package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePageCoursera {
    WebDriver driver;


	public HomePageCoursera(WebDriver driver) {
		 this.driver = driver;
	        PageFactory.initElements(driver, this);
	}
	


	    // Web elements
	    @FindBy(xpath = "//input[@id='search-autocomplete-input']")
	    WebElement searchBox;

	    @FindBy(xpath = "//div[@class='magnifier-wrapper']")
	    WebElement searchButton;
	   
	    @FindBy(linkText="For Campus")
	    WebElement forCampus;
	    
	    @FindBy(linkText="For Enterprise")
	    WebElement forEnterprise;
	    
	    public void searchPresence() {
	    	 Assert.assertTrue(searchBox.isDisplayed(), "Search box is not displayed on the webpage.");
	    }
        public void searchInput(String searchData) {
        	// Retrieve the value from the search box
            String enteredData = searchBox.getAttribute("value");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='';", searchBox);
            // Assert that the entered data matches the expected data
            Assert.assertEquals(enteredData, searchData, "The data in the search bar does not match the expected value.");
        }
        
	    // Methods to interact with elements
	    public void enterSearchQuery(String query) {
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='';", searchBox);
	        searchBox.sendKeys(query);
	    }

	    public void clickSearchButton() {
	    	 // Create an instance of Actions class
	        Actions actions = new Actions(driver);
	        // Press Enter
	        actions.sendKeys(Keys.ENTER).perform();	    
	     }

		public void validatelinktext() {
			
			forEnterprise.click();
			String urlForEnterprise="https://www.coursera.org/business?utm_campaign=website&utm_content=corp-to-home-footer-for-enterprise&utm_medium=coursera&utm_source=enterprise";
			 Assert.assertEquals(driver.getCurrentUrl(), urlForEnterprise);
			forCampus.click();
			String urlForCampus="https://www.coursera.org/campus?utm_campaign=website&utm_content=corp-to-home-footer-for-campus&utm_medium=coursera&utm_source=enterprise";
			 Assert.assertEquals(driver.getCurrentUrl(), urlForCampus);
		}
		
		
}