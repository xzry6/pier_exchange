package com.pier.exchange.util;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.log4j.Logger;

/**
 * 
 * @ClassName: ConfigUtil
* @Description: 鍏ㄥ眬涓婁笅鏂囨敞鍐岄厤缃簲鐢�
* @author Caliph.Chen clp_job@163.com
* @date 2014骞�11鏈�3鏃� 涓嬪崍3:05:17
 */
public class ConfigUtil {
	
	private static Logger LOG = Logger.getLogger(ConfigUtil.class);
	
	private static final CompositeConfiguration config = new CompositeConfiguration();
	
	static{
		try {
			LOG.info("properties value list delimiter is '|'");
			CompositeConfiguration.setDefaultListDelimiter('|');
			config.addConfiguration(new SystemConfiguration());
			config.addConfiguration(new PropertiesConfiguration("config.properties"));
		} catch (ConfigurationException e) {
			LOG.error("鍔犺浇閰嶇疆鏂囦欢鏃跺嚭鐜伴敊璇�", e);
		}
	}
	
	private ConfigUtil(){
		
	}
	
	/**
	 * 
	*@Title: addConfiguration
	* @Description: 娣诲姞閰嶇疆
	* @param @param configuration 
	*  PropertiesConfiguration: Loads configuration values from a properties file.
		XMLConfiguration: Takes values from an XML document.
		INIConfiguration: Loads the values from a .ini file as used by Windows.
		PropertyListConfiguration: Loads values from an OpenStep .plist file. XMLPropertyListConfiguration is also available to read the XML variant used by Mac OS X.
		JNDIConfiguration: Using a key in the JNDI tree, can retrieve values as configuration properties.
		BaseConfiguration: An in-memory method of populating a Configuration object.
		HierarchicalConfiguration: An in-memory Configuration object that is able to deal with complex structured data.
		SystemConfiguration: A configuration using the system properties 
	* @return void 
	* @throws
	 */
	public static void addConfiguration(Configuration configuration)
	{
		config.addConfiguration(configuration);
	}
	
	/**
	 * 
	*@Title: getConfig
	* @Description: 鑾峰彇閰嶇疆
	* @param @param configName
	* @param @param defalutValue
	* @param @return 
	* @return String 
	* @throws
	 */
	public static String getString(String configName,String defaultValue){
		String value = config.getString(configName, defaultValue);
		LOG.info("get config value, ["+configName+"]="+value);
		return value;
	}
	
	public static String getString(String key){
		String value = config.getString(key);
		LOG.info("get config value, ["+key+"]="+value);
		return value;
	}
	
	/**
	 * 杩斿洖Configuration,鍙互鑷繁璋冪敤Configuration閲岀殑鎺ュ彛锛�
	 * @return
	 */
	public static Configuration getConfig(){
		return config;
	}
}
