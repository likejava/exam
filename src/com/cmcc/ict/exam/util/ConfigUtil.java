package com.cmcc.ict.exam.util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public class ConfigUtil {
	static public String getPublicIp()
	{
		return getConfig("public_ip");
	}
	static public String getPublicPort()
	{
		return getConfig("public_port");
	}
	static public String getConfig(String key)
	{
		String value="";
		Properties prop = new Properties();
		InputStream in;
		try {
			//in = new BufferedInputStream (new FileInputStream("config.properties"));
			in = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(in);
			Iterator<String> it=prop.stringPropertyNames().iterator();
			while(it.hasNext()){
				if(key.equals(it.next()))
				{
					value = prop.getProperty(key);
					break;
				}
			}
			in.close();
		}
		catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
}
