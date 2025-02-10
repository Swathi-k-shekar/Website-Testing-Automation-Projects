package Tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.ForUniversities;
import Pages.HomePageCoursera;
import Utilites.ConfigReader;
import Utilites.ExelWrite;
import Utilites.ScreenShot;

//In Home page, go to "For Enterprise"; Look into Courses for Campus under Product; Fill the  "Ready to transform" form with any one input invalid (example: email); Capture the error message & display

public class ThirdFlow {
	  WebDriver driver;
	    HomePageCoursera homePage;
	    ForUniversities universePage;
	    ExelWrite writer;
	    ConfigReader config=new ConfigReader("C:\\Users\\2368585\\eclipse-workspace\\IdentifyCourses\\src\\test\\resources\\config.properties");
	    String url = ConfigReader.getProperty("url");
	    String driverPath=ConfigReader.getProperty("driverPath");
	    String filepath=ConfigReader.getProperty("FormErrorPath");
	   
	    
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
	    	universePage=new ForUniversities(driver);
	    	writer=new ExelWrite("Form Error Message");
	    }
	    
	    
	    @Test(priority=17)
	    public void forCampusValidation() {
	    	System.out.println("IC_TC_14_HomepageNavigation");
	    	homePage.validatelinktext();
	    }
	    
	    @Test(priority=18)
	    public void formAppearenceValidation() {
	    	System.out.println("IC_TC_15_FormAppearence");
	    	universePage.formValidation();
	    }
	    
	    @Test(priority=19)
	    public void formFeildsValidation() {
	    	System.out.println("IC_TC_16_FormFeilds");
	    	universePage.formfeildsValidation();
	    }
	    
	    
	    //@Test(priority = 20, dataProvider = "userData", dataProviderClass = JsonUtils.class)
	    @Test(priority=20)
	    public void formInputValidation() {
	    	System.out.println("IC_TC_17_FormInputFeed");
	    	//Read the Date from the utils file
	    	universePage.formFill(ConfigReader.getProperty("firstName"), ConfigReader.getProperty("lastName"),  ConfigReader.getProperty("phoneNumber"), ConfigReader.getProperty("email"), ConfigReader.getProperty("education"), ConfigReader.getProperty("address"), ConfigReader.getProperty("occupation"), ConfigReader.getProperty("interest"), ConfigReader.getProperty("additionalInfo"),ConfigReader.getProperty("country"));
	    }
	    
	    @Test(priority=21)
	    public void submitButtonValidation() {
	    	System.out.println("IC_TC_18_SubmitButton");
	    	universePage.formSubmit();
	    }
	    
	    @Test(priority=22)
	    public void inputErrorValidation()
	    {
	    	System.out.println("IC_TC_19_InvalidMessage");
	    	String errormsg=universePage.errorCapture();
	    	//Write to Exel	
	    	writer.writeLine(errormsg,0,"Invaild Email Error Msg:");
	    }	 
	    
	    @Test(priority=25)
	    public void screenPicture()  {
	    	ScreenShot.takeScreenshot(driver, ConfigReader.getProperty("ShotPath3"));
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
