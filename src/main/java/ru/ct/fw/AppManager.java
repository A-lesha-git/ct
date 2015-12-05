package ru.ct.fw;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import ru.ct.util.PropertyLoader;
import ru.ct.fw.TestBase;
import ru.ct.fw.WebDriverHelper;
import ru.ct.helpers.AutorithationHelper;
import ru.ct.helpers.NavigationHelper;


//import com.readRu.helpers.SikuliHelper;






public class AppManager {
	private static AppManager singleton;
	private WebDriverHelper webDriverHelper;
	private Properties propsF;
	private Properties propsP;
	private NavigationHelper navigationHelper;
	private AutorithationHelper autorithationHelper;
	protected String baseUrl;
	
	
//	private SikuliHelper silkuliHelper;
//	public AppManager() {
//			
//			webDriverHelper = new WebDriverHelper(this);
//			
//	}
	


	public void stop() {
		 webDriverHelper.stop();  
	}
	
	//�����������
	public static AppManager getInstance() {
		if (singleton == null) {
			singleton = new AppManager();
		}
		return singleton;
	}
	
	public String getProperty(String key){
		return propsF.getProperty(key);
	}
	
	public Properties getPropsF() {
		return TestBase.getPropsFile();
	}
	
	public Properties getPropsP() {
		return TestBase.getPropsProfile();
	}
	
	public String getProperty(String key, String defaultValue){
		return propsF.getProperty(key,defaultValue);
	}
	


	public boolean startWhithFireBug() {
		String afa = PropertyLoader.loadProperty("fireBugActive?", false);
		if(getPropsF().getProperty("fireBugActive?").equals("false")) {
		return false;
		} else {
		return true;
		}
	}

	public WebDriver getDriver() {
		return getWebDriverHelper().getDriver();
	}

	public  NavigationHelper getNavigationHelper() {
		if(navigationHelper == null)
		navigationHelper = new NavigationHelper(this);
		return navigationHelper;
		}
	
	public  AutorithationHelper getAutorithationHelper() {
		if(autorithationHelper == null)
			autorithationHelper = new AutorithationHelper(this);
		return autorithationHelper;
		}
	
//	public SikuliHelper getSikuliHelper() {
//		if(silkuliHelper == null)
//			silkuliHelper = new SikuliHelper(this);
//			return silkuliHelper;
//		}

	public void setPropertisP(Properties propsP) {
		this.propsP = propsP;
	}
	
	public void setPropertisF(Properties propsF) {
		this.propsF = propsF;
	}
	public WebDriverHelper getWebDriverHelper() {
		if(webDriverHelper==null)
		webDriverHelper = new WebDriverHelper(getMainUrl(),startWhithFireBug());
		return webDriverHelper;
	}

	
	public String getMainUrl() {
		
		String url = getPropsF().getProperty("url");
		if(url == null) {
			return "https://urla-net.ru";
			} else {
			return url;
			}
	}
	
	public String getDataProp(String val) throws IOException {
		Properties properties = new Properties();
		InputStream is = AppManager.class.getClassLoader().getResourceAsStream("application.properties");
		properties.load(is);
		System.out.println("Type of app: " + properties.getProperty("type"));
		return  properties.getProperty("type");
	}
	
	
	
	
	
}
