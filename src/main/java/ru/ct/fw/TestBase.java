package ru.ct.fw;



import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Logger;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import ru.ct.fw.AppManager;
import ru.ct.util.PropertyLoader;



public class TestBase {
	private Logger log = Logger.getLogger("TEST");
	protected AppManager app;
	public static Properties propsP = new Properties();
	public static Properties propsF = new Properties();
	InputStream stream = null;
	InputStreamReader reader = null;
	
	public static Properties getPropsProfile() {
		return propsP;
	}
	
	public static Properties getPropsFile() {
		return propsF;
	}
/*
	@BeforeClass
	protected void setUp() throws Exception {
		app = AppManager.getInstance();
		if (configFile == null) {
			configFile = "config";
		}
		InputStream stream;
		try {
			stream = new FileInputStream(new File(configFile));
			reader = new InputStreamReader(stream,"UTF-8");
			props.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	*/
	private static final String PROP_FILE_PROFILE = "/conf.properties";
	private static final String PROP_FILE = "/application.properties";
	@BeforeTest
	@Parameters({"configFile"})
	public void setUp(@Optional String configFile) throws Exception {
		
		if(configFile == null){
			configFile = "/conf.properties";
		}

		//Properties props = new Properties();
		try {
			propsP.load(PropertyLoader.class.getResourceAsStream(PROP_FILE_PROFILE));
			propsF.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}			
			
		log.info("SetUp start");
		app = new AppManager();
		app = AppManager.getInstance();
		app.setPropertisP(propsP);
		app.setPropertisF(propsF);
			
	  }
	
	@AfterTest
	public void tearDown() throws Exception {
		log.info("Tear down");
		app.stop();
	  }

	

}
