package miniProject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.*;
//import org.apache.poi.ss.usermodel.;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class FIndHotelAvilablity {
	
	 // Helper method to check for the presence of an element
    public static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
  
  
	public static void main(String[] args) throws InterruptedException {
		
	
		//Setting up driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\2368585\\eclipse-workspace\\Selenium_Project\\Driver\\chromedriver1.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Wait object for explicit wait
		WebDriverWait wait = new WebDriverWait(driver,10);
		//java script executer
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	        
		driver.get("https://www.trivago.in/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 // Enter city
        WebElement cityInput = driver.findElement(By.id("input-auto-complete"));
        cityInput.sendKeys("Mumbai");
        driver.findElement(By.className("ApipQA")).click();

        // Select check-in and check-out dates
//        WebElement checkInDate = driver.findElement(By.xpath("//span[@data-testid='search-form-calendar-checkin-value']"));
//        checkInDate.sendKeys("2024-07-28");
//
//        WebElement checkOutDate = driver.findElement(By.xpath("//span[@data-testid='search-form-calendar-checkout-value']"));
//        checkOutDate.sendKeys("2024-07-29");
        WebElement checkInOutDate = driver.findElement(By.xpath("//label[@data-testid='nextWeekend-index-label']"));
        checkInOutDate.click();

        // Select number of adults and rooms

        WebElement adultsInput = driver.findElement(By.xpath("//button[@class='_7AXo3Y']"));
        js.executeScript("arguments[0].click();", adultsInput);
    
        // Apply and search
        WebElement applyButton = driver.findElement(By.xpath("//button[@data-testid='guest-selector-apply']"));
        applyButton.click();

        WebElement searchButton = driver.findElement(By.xpath("//button[@data-testid='search-button-with-loader']"));
        searchButton.click();
        
        // Closing the calendar pop up if appeared in web page
        WebElement closeCalender= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='calendar-button-close']")));
        closeCalender.click();

//		Select Sort By “Rating only”
// Wait for the results to load and sort by rating
        
        try {
      	  
	          boolean condition1=isElementPresent(driver,By.xpath("//button[@name='sorting_selector']//span/span/span"));
	          if(condition1){
	          	 //locate drop down
	              WebElement sortByRating =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='sorting_selector']//span/span/span")));
	              sortByRating.click();
	              //select option
	              WebElement sortByRatingelemt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[5]/label/input")));
	              sortByRatingelemt.click();
	              //Apply the OPtion RateBy
	              WebElement applyRating=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='filters-popover-apply-button']")));
	              applyRating.click();
	             }
	          }
	          catch(NoSuchElementException e) {
	        	 System.out.println("Sort By Not in the Div");
	          }
	          finally {
	          
	          try {
	        	  //Thread.sleep(1000);
	          boolean condition2=isElementPresent(driver,By.xpath("//select[@data-testid='sorting-selector-select']"));
	          if(condition2) {
	          	//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@data-testid='sorting-selector-select']")));
	        	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"sorting-selector\"]/option[5]")));
	          	WebElement rate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@data-testid='sorting-selector-select']")));
	              Select sc= new Select(rate); 
	              sc.selectByVisibleText("Rating only");}
	          }

	          catch(NoSuchElementException e){
	        	  System.out.println("Sort By Not in the Section");
	          }
	          
	          
	          
	          finally {

        
        // Fetch the details of hotels such as rent,name,Rating:
        Thread.sleep(900);
        
        String  nameElemt=driver.findElement(By.xpath("//li[@data-testid='accommodation-list-element']//div[2]//span")).getText();
        System.out.println(nameElemt);
        String  priceElemt=driver.findElement(By.xpath("//div[@class='rf4t8v']/span")).getText();
        System.out.println(priceElemt);
        String  ratingElemt=driver.findElement(By.xpath("//span[@data-testid='aggregate-rating']/span/span")).getText();
        System.out.println(ratingElemt);
     
       
//      1) Availability of hotel rooms in next week (e.g. 27 July Check in and 28 July Check out) for a specific city. Ex: Mumbai
//      2) Get the Price of All the Hotels
        // Get hotel prices and ratings
        List<WebElement> hotels = driver.findElements(By.xpath("//ol[@data-testid='accommodation-list']/li"));
        List<Hotel> hotelData = new ArrayList<>();
        // Create a new workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        // Create a new sheet
        XSSFSheet sheet = workbook.createSheet("Data");
     // Create header row
    	XSSFRow dataRow = sheet.createRow(0);
        //Hotel name passing to excel
    	dataRow.createCell(0).setCellValue("Hotel Name");
    	dataRow.createCell(1).setCellValue("Price");
    	dataRow.createCell(2).setCellValue("Rating");
        
        int rowCunt=0;
        for (WebElement hotel : hotels) {
        	// Create rows
        	dataRow = sheet.createRow(++rowCunt);
        	//Hotel Name Data
            String name = hotel.findElement(By.xpath(".//button[@data-testid='item-name']/span")).getText();
            System.out.println(name);
            dataRow.createCell(0).setCellValue(name);
 
            String price = hotel.findElement(By.xpath(".//div[@class='rf4t8v']/span")).getText();
            System.out.println(price);
            //Hotel Price passing to excel
            dataRow.createCell(1).setCellValue(price);
            
            String rating = hotel.findElement(By.xpath(".//span[@data-testid='aggregate-rating']/span/span")).getText();
            System.out.println(rating);
            hotelData.add(new Hotel(name, price, Double.parseDouble(rating)));
            //Hotel Rating passing to excel
            dataRow.createCell(2).setCellValue(rating);
            
        }
        
//        3) Sort the hotels by rating

        Collections.sort(hotelData, Comparator.comparingDouble(Hotel::getRating).reversed());
        
//      4) Check if the first 5 hotels belongs to the specific city searched for. Ex: Mumbai
        dataRow=sheet.createRow(rowCunt+4);
        dataRow.createCell(0).setCellValue("Check if the first 5 hotels belongs to the specific city searched for. Ex: Mumbai");
        for (int i = 0; i < 5 && i < hotelData.size(); i++) {
        	dataRow=sheet.createRow(++rowCunt);
            Hotel hotel = hotelData.get(i);
            if (!hotel.getName().contains("Mumbai")) {
                System.out.println("Hotel " + hotel.getName() + " is not in Mumbai");
                dataRow.createCell(0).setCellValue("Hotel " + hotel.getName() + "      IS NOT in Mumbai");
            } else {
                System.out.println("Hotel " + hotel.getName() + " is in Mumbai");
                dataRow.createCell(0).setCellValue("Hotel" + hotel.getName() + "       IS in Mumbai");
            }
        }
        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream("C:\\Users\\2368585\\eclipse-workspace\\Selenium_Project\\Exelsheet\\name.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        // Close the browser
        driver.quit();
    }
}
}
}




