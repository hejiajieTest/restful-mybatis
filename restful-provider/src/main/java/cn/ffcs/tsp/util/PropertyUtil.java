package cn.ffcs.tsp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtil {

	private static Map<String, Map<String, String>> properties = new HashMap();
	
	private static Properties prop = new Properties() ;
	 public static Map<String, String> getProperties(String path) {
	        if (!properties.containsKey(path)) {
	            Properties tempProperties = loadProperties(path);
	            properties.put(path, new HashMap(tempProperties));
	            tempProperties.clear();
	        }

	        return (Map)properties.get(path);
	  }
	static{
		loadFile("jettyServer.properties");
	}
	public static Properties loadFile(String fileName){
		try {
			URL url = PropertyUtil.class.getClassLoader().getResource(fileName );
			InputStream in = new FileInputStream(url.getPath()) ;
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static Properties loadProperties(String fileName) {
        Properties prop = new Properties();
        try {
//            filePath = FileUtil.getFilePath("config.properties",Constants.Env.BASE_HOME);
            InputStream input = new FileInputStream(getFile(fileName, "configs/"));
            prop.load(input);
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return prop;
    }
//	
//	public static String getString(String key){
//		String value = prop.getProperty(key);
//		return value;
//		
//	}

	
	public static File getFile( String fileName, String propertyName ) throws Exception {
		String filePath = null;
		if ( propertyName != null && ! "".equals( propertyName ) ) {
			filePath = System.getProperty( propertyName );
		}
		File file = null;
		
		if ( filePath == null || "".equals( filePath ) ) {
			URL url = PropertyUtil.class.getClassLoader().getResource(propertyName+fileName );
			if ( url == null ) {
				throw new FileNotFoundException( fileName + " not found!" );
			}
			file = new File( url.getPath() );
		} else {
			filePath = filePath.endsWith( "/" ) ? filePath.concat( fileName ) 
					: filePath.concat( "/" ).concat(  fileName );
			file = new File( filePath );
		}
		return file;
	}
	
	
	public static Properties getProp() {
		return prop;
	}

	public static void main(String[] args) {
		String ss = PropertyUtil.getProperties("jettyServer.properties").get("server.name");
		System.out.println(ss);
//		String sss = PropertyUtil.loadFile("jettyServer.properties").getProperty("server.name");
//		System.out.println(sss);
		
	}
}
