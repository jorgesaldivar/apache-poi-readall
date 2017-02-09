package com.saldivar.reader;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class App {

	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {

		Map<String, String> properties = new PropertyReader().readProperty();
		if (properties != null)
			new Process(properties).run();

	}
}
