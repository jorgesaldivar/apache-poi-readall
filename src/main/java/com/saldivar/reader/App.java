package com.saldivar.reader;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class App {
	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
		System.out.println("Hello World!");

		ClassLoader classLoader = App.class.getClassLoader();
		File file = new File(classLoader.getResource("sample.xlsx").getFile());

		String header = "ID";
		Workbook wb = WorkbookFactory.create(file);

		int numberOfSheets = wb.getNumberOfSheets();

		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = wb.getSheetAt(i);

			int x = sheet.getFirstRowNum();

			if (x >= 0) {
				Row row = sheet.getRow(x);
				short s = row.getLastCellNum();
				boolean found = false;
				for (short y = 0; y < s; y++) {
					System.out.println(row.getCell(y));
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
						System.out.println((int) row.getCell(s).getNumericCellValue());
					}
				}
			}

		}

		wb.close();

	}
}
