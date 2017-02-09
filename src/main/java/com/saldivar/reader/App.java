package com.saldivar.reader;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class App {

	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {

		String header = new PropertyReader().readProperty();
		if (header != null)
			new Process().run(header);

	}
}
