package Tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.HomePageCoursera;
import Pages.LangugeLearning;
import Utilites.ConfigReader;
import Utilites.ExelWrite;
import Utilites.ScreenShot;

public class SecondFlow {

	 WebDriver driver;
	 HomePageCoursera homePage;
	 LangugeLearning learnPage;
	 ExelWrite writer;
	  ConfigReader config=new ConfigReader("C:\\Users\\2368585\\eclipse-workspace\\IdentifyCourses\\src\\test\\resources\\config.properties");
	  String url = ConfigReader.getProperty("url");
	  String driverPath=ConfigReader.getProperty("driverPath");
	  String filepath=ConfigReader.getProperty("LangLearningPath");
	   
	
	    @BeforeClass
	    public void setUp() {
		   System.out.println("Before Test");
	    	System.setProperty("webdriver.chrome.driver", driverPath);
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get(url);
	    	// Set implicit wait
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    	homePage=new HomePageCoursera(driver);
	    	learnPage=new LangugeLearning(driver);
	    	writer=new ExelWrite("LangugeLearn");
	    }
	    
	    @Test(priority=13)
	    public void explore() {
	    	System.out.println("IC_TC_20_explore");
	    	learnPage.laungeLearningApperance();
	    }
	    
	    @Test(priority=14)
	    public void langLearn() {
	    	System.out.println("IC_TC_21_Language_Learn");
	    	learnPage.learnLang();
	    }
	    
	    @Test(priority=15)
	    public void extractLangLearn() {
	    	System.out.println("IC_TC_22_Extract_Languages");
	    	String[] langDetials=learnPage.extractLang();
	    	//pass to Exel
	    	writer.writeData(langDetials, 0, "Languges:");
	    }
	    @Test(priority=16)
	    public void extractLangLevels() {
	    	System.out.println("IC_TC_23_Extract_Languages");
	    	String[] levelDetials=learnPage.extractLevels();
	    	//pass to Exel
	    	writer.writeData(levelDetials, 10, "Levels:");
	    }
	    
	    @Test(priority=24)
	    public void screenPicture()  {
	    	ScreenShot.takeScreenshot(driver, ConfigReader.getProperty("ShotPath2"));
	    }
	    
	    @AfterClass
	    public void tearDown() {
	    	 //Save the Excel file
	         try {
	             writer.saveToFile(filepath);
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	    	
	      // Close the browser
	        driver.quit();
	    }
}
