package com.saldivar.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyReader {

	public Map<String, String> readProperty() {

		Map<String, String> propertiesVariables = null;

		ClassLoader classLoader = getClass().getClassLoader();
		Properties properties = new Properties();
		InputStream input = null;

		try {

			File file = new File(classLoader.getResource("application.properties").getFile());
			input = new FileInputStream(file);
			properties.load(input);

			String header = properties.getProperty("header");
			String propertyFile = properties.getProperty("file");
			if (header != null && propertyFile != null) {
				System.out.println("Header: " + header + " - File name: " + propertyFile);
				propertiesVariables = new HashMap<>();
				propertiesVariables.put("header", header);
				propertiesVariables.put("file", propertyFile);
			}

		} catch (IOException io) {

			io.printStackTrace();

		} finally {

			if (input != null) {

				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

		return propertiesVariables;

	}
}
