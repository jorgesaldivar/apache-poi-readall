package com.saldivar.reader;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Process {
	
	public void run(String header) throws EncryptedDocumentException, InvalidFormatException, IOException {

		ClassLoader classLoader = Process.class.getClassLoader();
		File file = new File(classLoader.getResource("sample.xlsx").getFile());
		
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
