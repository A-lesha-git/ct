package ru.ct.fw;


import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import ru.ct.fw.AppManager;

public class WebDriverHelper {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private AppManager manager;
	public static FirefoxProfile FFprofile = new FirefoxProfile();
	
	
	public WebDriverHelper(String mainUrl, boolean b){
		startWithFireBug(b);
		this.manager = manager;
		driver.manage().deleteAllCookies();
		driver.get(mainUrl);
		
		/*
		
		driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(
	    		Integer.parseInt(manager.getProperty("implicitWait", "30")), 
	    		TimeUnit.SECONDS);
	    driver.get(manager.getProperty("baseUrl"));
	    
	    */
	}
	
	
	private void startWithFireBug(boolean profile) {
		if (profile == true) {
			File profileDir = new File("rheb0pza.default-1370848873096");
			FFprofile = new FirefoxProfile(profileDir);
			driver = new FirefoxDriver(FFprofile);
		} else {
			driver = new FirefoxDriver(FFprofile);
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// запустить драйвер без настроенного профиля(firebug'a и тд)
		//driver = new FirefoxDriver(FFprofile);
		
	}
	public void stop() {
		 driver.quit();
		    String verificationErrorString = verificationErrors.toString();
//		    if (!"".equals(verificationErrorString)) {
//		      fail(verificationErrorString);
//		    }
	}

	public WebDriver getDriver() {
		return driver;
	}

	
}
