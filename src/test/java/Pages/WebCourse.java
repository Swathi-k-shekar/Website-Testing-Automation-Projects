package Pages;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class WebCourse {
	WebDriver driver;
	WebDriverWait wait;
	
	
	public WebCourse(WebDriver driver) {
		 this.driver = driver;
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//div[@data-e2e='NumberOfResultsSection']/span")
    WebElement textBox;
	
    @FindBy(className="css-1asa8xy")
    WebElement section;
    
    @FindBy(id="cds-react-aria-18")
    WebElement langugesCheckBox;
    @FindBy(id="cds-react-aria-50")
    WebElement levelsCheckBox;
    
    @FindBy(xpath="//div[@data-testid='active-filter-items']/button")
    WebElement filterTextLang;
    @FindBy(xpath="//div[@data-testid='active-filter-items']/button[2]")
    WebElement filterTextLevl;
    
    
    @FindBy(xpath = "//a[@data-track-component='search_card']/h3")
    List<WebElement> courseName;
    
    @FindBy(xpath = "//div[@class='cds-CommonCard-metadata']/p[@class=' css-vac8rf']")
    List<WebElement> courseDuration;
    
    @FindBy(xpath = "//div[@class='cds-RatingStat-meter']//span")
    List<WebElement> courseRating;
    
    @FindBy(xpath="//div[@data-testid='search-filter-group-Language']//div[@class='cds-checkboxAndRadio-labelText']/span/span")
    List<WebElement> languges;
    
    @FindBy(xpath="//button[@data-track-component='expand_filter_items_button_language']")
    WebElement showMore;
    
    @FindBy(xpath="//button[@aria-label='Show less Language options']")
    WebElement showLess;
    
    @FindBy(xpath="//div[@data-testid='search-filter-group-Level']//div[@class='cds-checkboxAndRadio-labelText']/span/span")
    List<WebElement> levels;
	
	public String textValidation() {
		String text=textBox.getText();
		System.out.println( text);
		return text;
	}
	
	public void filterByPresence() {
		Assert.assertTrue(section.isDisplayed(), "Search box is not displayed on the webpage.");
	}
	public void setLangugeCheckBox(){
		langugesCheckBox.click();
	    wait.until(ExpectedConditions.elementToBeSelected(langugesCheckBox));
	    
		boolean isChecked = langugesCheckBox.isSelected();
        Assert.assertTrue(isChecked, "Checkbox is not checked!");
	}
	
	public void setLevelsCheckBox() {
		levelsCheckBox.click();
	    wait.until(ExpectedConditions.elementToBeSelected(levelsCheckBox));
		boolean isChecked = levelsCheckBox.isSelected();
        Assert.assertTrue(isChecked, "Checkbox is not checked!");
		}
	
	public void filterTextConformation() {
		wait.until(ExpectedConditions.visibilityOf(filterTextLang));
		String filterSelctLan=filterTextLang.getText();
		System.out.println(filterSelctLan.substring(0,17));
		
		wait.until(ExpectedConditions.visibilityOf(filterTextLevl));
		String filterSelctLvl=filterTextLevl.getText();
		System.out.println(filterSelctLvl.substring(0,8));
		
		Assert.assertEquals(filterSelctLan.substring(0,17), "Language: English", "The strings do not match.");
		Assert.assertEquals(filterSelctLvl.substring(0,8), "Beginner", "The strings do not match.");
		
	}

	public String[][] extractDetials() {
		  String[][] data = new String[2][3];

		  data[0][0] = courseName.get(0).getText();
	        String[] dur1= courseDuration.get(0).getText().split(" ");
	        data[0][1]=dur1[dur1.length-4]+" "+dur1[dur1.length-3]+" "+dur1[dur1.length-2]+" "+dur1[dur1.length-1]; 
	        data[0][2] = courseRating.get(0).getText();

	        data[1][0] = courseName.get(1).getText();
	        String[] dur2= courseDuration.get(1).getText().split(" ");
	        data[1][1]=dur2[dur2.length-4]+" "+dur2[dur2.length-3]+" "+dur2[dur2.length-2]+" "+dur2[dur2.length-1]; 
	        data[1][2] = courseRating.get(1).getText();

		return data;
	}


	public String[] langugeExtract() {
		
		showMore.click();
		wait.until(ExpectedConditions.visibilityOf(showLess));
		String[] data = new String[languges.size()];
		for(int i=0;i<languges.size(); i++) {
			data[i]=languges.get(i).getText();
			//System.out.println(data[i]);
		}
		
		return data;
		
	}
	public int  langugesCount() {
		
		return languges.size();
	}
	public int  levelsCount() {
		
		return levels.size();
	}
	
	public String[] levelsExtract() {
		String[] data = new String[levels.size()];
		for(int i=0;i<levels.size(); i++) {
			data[i]=levels.get(i).getText();
			//System.out.println(data[i]);
		}
		
		return data;
		
	}
	
	
}
