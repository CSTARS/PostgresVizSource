package edu.cstars.vizsource;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ServerProperties {
	
	public static String propFile = "/etc/vizsource/setup.properties";
	public Properties configFile;
	
	public String DB_URL;
	public String DB_USERNAME;
	public String DB_PASSWORD;
	public String DB_SCHEMA;

	private boolean error = false;
	
	// some calls might not need us to parse properties, so lets do this here
	public ServerProperties(){
		configFile = new Properties();
		try {
		     configFile.load(new FileInputStream(new File(propFile)));
		} catch (Exception e) {
			error = true;
			System.out.println("Unable to load config file: "+propFile+": "+e.getMessage());
		}
		if( !error ){
			// DB
			DB_URL = configFile.getProperty("DB_URL");
			DB_USERNAME = configFile.getProperty("DB_USERNAME");
			DB_PASSWORD = configFile.getProperty("DB_PASSWORD");
			DB_SCHEMA = configFile.getProperty("DB_SCHEMA");
		}
	}
	
	public boolean hasError(){
		return error;
	}

}
