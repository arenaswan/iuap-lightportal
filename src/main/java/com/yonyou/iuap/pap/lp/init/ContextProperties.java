package com.yonyou.iuap.pap.lp.init;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ContextProperties{
    private static Logger logger = LoggerFactory.getLogger(ContextProperties.class);
	
	private static final String PROPERTIES_RESOURCE_LOCATION = "application.properties";
	private static final Properties localProperties = new Properties();

    static {
		try {
			ClassLoader cl = ContextProperties.class.getClassLoader();
			URL url = (cl != null ? cl.getResource(PROPERTIES_RESOURCE_LOCATION) :
					ClassLoader.getSystemResource(PROPERTIES_RESOURCE_LOCATION));
			if (url != null) {
				logger.info("Found 'application.properties' file in local classpath");
				InputStream is = url.openStream();
				try {
					localProperties.load(is);
				}
				finally {
					is.close();
				}
			}
		}
		catch (IOException ex) {
			if (logger.isInfoEnabled()) {
				logger.info("Could not load 'application.properties' file from local classpath: " + ex);
			}
		}
	}

    public static String getSysid() {
		return getProperty("sysid","wbalone");
	}

	public static String getUserType() {
		return getProperty("workbench.4a.user.type","default");
	}

	public static String getUserCenter() {
		return getProperty("workbench.4a.user.userCenter");
	}

	public static void setProperty(String key, String value) {
		if (value != null) {
			localProperties.setProperty(key, value);
		}
		else {
			localProperties.remove(key);
		}
	}

	public static String getProperty(String key) {
		String value = localProperties.getProperty(key);
		if (value == null) {
			try {
				value = System.getProperty(key);
			}
			catch (Throwable ex) {
				if (logger.isDebugEnabled()) {
					logger.debug("Could not retrieve system property '" + key + "': " + ex);
				}
			}
		}
		return value;
	}
	
	public static String getProperty(String key,String defaultValue) {
		String val = getProperty(key);
		return val!=null?val:defaultValue;
	}
	
	private ContextProperties(){}
}