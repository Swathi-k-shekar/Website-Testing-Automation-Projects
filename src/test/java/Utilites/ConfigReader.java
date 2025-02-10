package Utilites;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class ConfigReader {

	    public static Properties properties;

	    public ConfigReader(String filePath) {
	        properties = new Properties();
	        try {
	            FileInputStream fis = new FileInputStream(filePath);
	            properties.load(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	  

		public static String getProperty(String key) {
			 return properties.getProperty(key);
		}
}

