package test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class xlsClasses {

	public String path;
	public static FileInputStream fis = null;
	public static XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	public File src = null;

	public xlsClasses(String path, String sheet_name) {

		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheet_name);
			src = new File(sheet_name);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void changeSheet(String sheetName) {
		sheet = workbook.getSheet(sheetName);
	}

	public int[] getRowCols(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			int[] loc = { -1, -1 };
			return loc;
		} else {
			sheet = workbook.getSheetAt(index);
			int[] loc = { sheet.getLastRowNum() + 1, sheet.getRow(1).getLastCellNum() + 1 };
			return loc;

		}
	}

	public String getCellString(int r, int c) {

		this.row = sheet.getRow(r);
		cell = this.row.getCell(c);
		String s = cell.getStringCellValue();
		return s;
	}

	public double getCellDoubleValue(int r, int c) {

		row = sheet.getRow(r);
		cell = row.getCell(c);
		double n = cell.getNumericCellValue();
		return n;
	}

	public boolean getCellBoolValue(int r, int c) {

		row = sheet.getRow(r);
		cell = row.getCell(c);
		boolean b = cell.getBooleanCellValue();
		return b;
	}

	public static void closeReaders() throws IOException {
		workbook.close();
		fis.close();

	}

}
