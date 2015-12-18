package ru.ct.fw;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ru.ct.util.PropertyLoader;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	String type = ru.ct.util.PropertyLoader.loadProperty("type",true);
    	System.out.println(type);
    }
}
