package com.saldivar.reader;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Process {

	private String header;
	private String fileProperty;
	private long counter = 0;

	public Process(Map<String, String> properties) {
		this.header = properties.get("header");
		this.fileProperty = properties.get("file");
	}

	public void run() throws EncryptedDocumentException, InvalidFormatException, IOException {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = null;

		try {
			file = new File(classLoader.getResource(fileProperty).getFile());
		} catch (NullPointerException e) {
			System.out.println("File not found");
			return;
		}

		Workbook wb = WorkbookFactory.create(file);

		int numberOfSheets = wb.getNumberOfSheets();

		System.out.println();

		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = wb.getSheetAt(i);
			processAllSheets(sheet);
		}

		System.out.println("\n\nRecords: " + counter);
		wb.close();

	}

	private void processAllSheets(Sheet sheet) {

		int x = sheet.getFirstRowNum();

		if (x >= 0) {

			Row row = sheet.getRow(x);

			short s = -1;
			if (row != null)
				s = row.getLastCellNum();

			boolean found = false;
			if (s > -1) {
				for (short y = 0; y < s; y++) {
					if (row.getCell(y).toString().equals(header)) {
						found = true;
						s = y;
						break;
					}
				}

				if (found) {
					int lastRow = sheet.getLastRowNum();

					for (int z = (x + 1); z <= lastRow; z++) {
						row = sheet.getRow(z);
						try {

							System.out.print((int) row.getCell(s).getNumericCellValue());
							if (++counter % 1000 == 0)
								System.out.println();
							else
								System.out.print(", ");
						} catch (NullPointerException | IllegalStateException e) {
						}
					}
				}
			}
		}

	}
}
