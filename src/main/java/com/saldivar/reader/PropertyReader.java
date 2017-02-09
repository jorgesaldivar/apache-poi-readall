package com.saldivar.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	public String readProperty () {

		Properties properties = new Properties();
		InputStream input = null;
		String header = null;
		ClassLoader classLoader = PropertyReader.class.getClassLoader();

		try {

			File file = new File(classLoader.getResource("application.properties").getFile());
			input = new FileInputStream(file);
			properties.load(input);

			header = properties.getProperty("header");
			if (header != null)
				System.out.println("Header: " + header);

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

		return header;

	}
}
