package com.li.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 鍔犺浇閰嶇疆鏂囦欢锛岀劧鍚庤繑鍥濪ataSource鏁版嵁婧�
 * @author Administrator
 */

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class PropertiesUtils {

	private static Properties properties = new Properties();

	static {
		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("db.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static DataSource getDataSource() {
		try {
			return BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
