package bernhard.scharrer.battlemobs.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

public class PluginProperties {
	
	private static final File PROPERTIES_FILE = new File("settings.properties");
	private static SortedProperties props;
	
	/**
	 * loads properties in from file
	 */
	public static void load() {
		
		props = new SortedProperties();
		
		if (!PROPERTIES_FILE.exists()) {
			createDefaultProperties();
		} else {
			try {
				props.load(new FileInputStream(PROPERTIES_FILE));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * this method is called when no propertie file exists.
	 * it creates default values and saves them into a file.
	 */
	private static void createDefaultProperties() {
		
		props.put(Props.SQL_HOST.toString(), "127.0.0.1");
		props.put(Props.SQL_PORT.toString(), "3306");
		props.put(Props.SQL_NAME.toString(), "root");
		props.put(Props.SQL_DATABASE.toString(), "dbname");
		props.put(Props.SQL_PASSWORD.toString(), "password");
		props.put(Props.SQL_NOPASSWORD.toString(), "false");
		
		try {
			props.store(new FileOutputStream(PROPERTIES_FILE), "Pokemon-Palladium Server Settings");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param propertie you want to get
	 * @return string value from property file
	 */
	public static String getString(Props prop) {
		return props.getProperty(prop.toString().toUpperCase());
	}
	
	/**
	 * @param propertie you want to get
	 * @return int value from property file
	 */
	public static int getInt(Props prop) {
		return Integer.parseInt(props.getProperty(prop.toString().toUpperCase()));
	}
	
	/**
	 * @param propertie you want to get
	 * @return int value from property file
	 */
	public static boolean getBoolean(Props prop) {
		return Boolean.parseBoolean(props.getProperty(prop.toString().toUpperCase()));
	}
	
	/**
	 * 
	 * @author Bernhard Scharrer
	 * 
	 * sorted representation of properties
	 *
	 */
	private static class SortedProperties extends Properties {
		
		@Override
		public synchronized Enumeration<Object> keys() {
			
			Enumeration<Object> keysEnum = super.keys();
			List<String> string_list = new ArrayList<String>();
			while (keysEnum.hasMoreElements()) {
				string_list.add((String) keysEnum.nextElement());
			}
			Collections.sort(string_list);
			Vector<Object> obj_list = new Vector<Object>();
			for (String s : string_list) obj_list.add(s);
			return obj_list.elements();
			
		}
		
	}
	
}