package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBconect {

	public Map<String, String>  getDBinfo() throws FileNotFoundException {

		String db_properties_file = "/Applications/Eclipse_2020-12.app/Contents/workspace/AttendanceManagement/DBconect.properties";

		Properties db_info = new Properties();
		FileInputStream db_file_stream = new FileInputStream(db_properties_file);

		try {
			db_info.load(db_file_stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String db_url = db_info.getProperty("url");
		String db_user = db_info.getProperty("user");
		String db_pass = db_info.getProperty("password");

		Map<String,String> getDBinfoForMap = new HashMap<>();

		getDBinfoForMap.put("url", db_url);
		getDBinfoForMap.put("user", db_user);
		getDBinfoForMap.put("url", db_pass);

		return getDBinfoForMap;
	}
}