package ru.ct.util;

import java.io.IOException;
import java.util.Properties;

/*
 * Class that extracts properties from the prop file.
 * 
 * @author Sebastiano Armeli-Battana
 */
public class PropertyLoader {

	private static String PROP_FILE = "/conf.properties";

	public static String loadProperty(String name, boolean isProfile) {
		if(!isProfile){
			PROP_FILE = "/application.properties";
		}else{
			PROP_FILE = "/conf.properties";
		}
		Properties props = new Properties();
		try {
			props.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String value = "";

		if (name != null) {
			value = props.getProperty(name);
		}
		return value;
	}
}