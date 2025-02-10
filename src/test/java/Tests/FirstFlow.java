package Tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.HomePageCoursera;
import Pages.WebCourse;
import Utilites.ConfigReader;
import Utilites.ExelWrite;
import Utilites.ScreenShot;



public class FirstFlow {
	    WebDriver driver;
	    HomePageCoursera homePage;
	    WebCourse coursePage;
	    ExelWrite writer;
	    ScreenShot screenShot;
	    
	    ConfigReader config=new ConfigReader("C:\\Users\\2368585\\eclipse-workspace\\IdentifyCourses\\src\\test\\resources\\config.properties");
	    String url = ConfigReader.getProperty("url");
	    String driverPath=ConfigReader.getProperty("driverPath");
	    String filepath=ConfigReader.getProperty("webCoursePath");
	    
	   
	   @BeforeTest
	    public void setUp() {
		   System.out.println("Before Test");
	    	System.setProperty("webdriver.chrome.driver", driverPath);
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get(url);
	     // Set implicit wait
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    	homePage=new HomePageCoursera(driver);
	    	coursePage=new WebCourse(driver);
	    	writer = new ExelWrite("CourseraFile.xlsx");
	    	screenShot=new ScreenShot();
	    }
	    
	    
	    @Test(priority=1)
	    public void urlValidation() {
	    	System.out.println("IC_TC_01_URL");
	    	Assert.assertTrue(driver.getTitle().contains("Coursera"));
	    }
	    
	    @Test(priority=2)
	    public void searchBarAppearence() {
	    	System.out.println("IC_TC_02_Appearence");
	    	homePage.searchPresence();
	    }
	    @Test(priority=3)
	    public void validateSearchBar() {
	    	System.out.println("IC_TC_03_Functionality");
//	    	from utils
	        homePage.enterSearchQuery(ConfigReader.getProperty("Input"));
	        homePage.searchInput(ConfigReader.getProperty("Input"));  
	        
	    }
	    @Test(priority=4)
	    public void validateSearchButton() {
	    	System.out.println("IC_TC_04_search");
	        homePage.clickSearchButton();
	        Assert.assertTrue(driver.getTitle().contains("Top Web Development Courses"));
	    }
	    

	    
	    @Test(priority=5)
	    public void resultTextValidation() {
	    	System.out.println("IC_TC_05_ResultText");
	        String result=coursePage.textValidation();
	        Assert.assertEquals(result, "Results for \"Web development courses\"", "The strings do not match.");
	       
	    }
	    
	    @Test(priority=6)
	    public void filterBySectionValidation()  {
	    	System.out.println("IC_TC_06_FilterBy");
	        coursePage.filterByPresence();
	    }
	    
	    @Test(priority=7)
	    public void validateLangugeCheckBox()  {
	    	System.out.println("IC_TC_07_FilterLanguage");
	        coursePage.filterByPresence();
	        coursePage.setLangugeCheckBox();
	    }
	    @Test(priority=8)
	    public void validateLevelsCheckBox()  {
	    	System.out.println("IC_TC_08_FilterLevel");
	        coursePage.filterByPresence();
	        coursePage.setLevelsCheckBox();
	    }
	    
	    @Test(priority=9)
	    public void validateFiltersVisiblity()  {
	    	System.out.println("IC_TC_09_FilterText");
	        coursePage.filterTextConformation();
	    }
	    
	    @Test(priority=10)
	    public void courseResult()  {
	    	System.out.println("IC_TC_10_courseResult");
	    	
	        String[][] courseDetails=coursePage.extractDetials();
	      //pass it to exel
	        
	        for (int i = 0; i < 2; i++) {
	            System.out.println("Course " + (i + 1) + ":");
	            System.out.println("Name: " + courseDetails[i][0]);
	            System.out.println("Total Learning Hours: " + courseDetails[i][1]);
	            System.out.println("Rating: " + courseDetails[i][2]);
	        }
	        
	        writer.writeData(courseDetails, 0,"Course Detials");
	        
	    }
	    
	    @Test(priority=11)
	    public void extractLanguges()   {
	    	System.out.println("IC_TC_11_ExtractLanguage");
	    	coursePage.filterByPresence();
	        String[] langDetials=coursePage.langugeExtract();
	        for(String elemt:langDetials) {
	        	System.out.println(elemt);
	        }
	        writer.writeData(langDetials, 7,"Languges Detials");
	       
	        int langs=coursePage.langugesCount();
	        System.out.println("Number of Laungages Found: "+langs);
	    }
	    
	    @Test(priority=12)
	    public void extractLevels()  {
	    	System.out.println("IC_TC_12_ExtractLevels");
	        coursePage.filterByPresence();
	        String[] levelDetials=coursePage.levelsExtract();
	        for(String elemt:levelDetials) {
	        	System.out.println(elemt);
	        }
	      //pass it to Excel
	        writer.writeData(levelDetials, 40,"Levels Detials");
	     
	        int lvls=coursePage.levelsCount();
	        System.out.println("Number of Levels Found: "+lvls);
	    }
	    
	    @Test(priority=23)
	    public void screenPicture()  {
	    	ScreenShot.takeScreenshot(driver, ConfigReader.getProperty("ShotPath1"));
	    }
	    
	    @AfterTest
	    public void tearDown() {
	    	System.out.println("After Test");
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
