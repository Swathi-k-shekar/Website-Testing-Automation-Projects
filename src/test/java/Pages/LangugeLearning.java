package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LangugeLearning {
	WebDriver driver;
	Actions actions;
     
	public LangugeLearning(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
		 actions = new Actions(driver);
	}
	
	  @FindBy(xpath="//span[@class='css-12xwvkc']")
	  WebElement elementToHover;
	  
	  @FindBy(id="language-learning~menu-item")
	  WebElement langLearn;
	  
	  @FindBy(xpath="//ul[@aria-labelledby='Language-Learning-tab-Popular-skills-title']//div/a")
	  List<WebElement> languges;
	  
	  @FindBy(xpath="//ul[@aria-labelledby='Language-Learning-tab-Get-started-title']//a")
	  List<WebElement> levels;
	
	public void laungeLearningApperance() { 
		// Perform the mouse hover action
        actions.moveToElement(elementToHover).perform();
	}

	public void learnLang() {
        // Perform the mouse hover action
        actions.moveToElement(langLearn).perform();	
	}


	public String[] extractLang() {
		String[] data=new String[languges.size()];
		for(int i=1;i<languges.size();i++) {
			data[i]=languges.get(i).getText();
			System.out.println(data[i]);
		}
		return data;
	}

	public String[] extractLevels() {
		String[] data=new String[levels.size()];
		for(int i=0;i<levels.size();i++) {
			data[i]=levels.get(i).getText();
			System.out.println(data[i]);
		}
		return data;
	}
	
}
