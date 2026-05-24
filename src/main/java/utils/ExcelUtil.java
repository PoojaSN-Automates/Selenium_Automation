package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	//To read data from Excel Sheet
	@SuppressWarnings("resource")
	public static Object[][] getTestData(String sheetname){
		
		String path= System.getProperty("user.dir") + "/TestData/testdata.xlsx";
		System.out.println("📂 Path: " + path);
		Object[][] data= null;
		
		try {
            FileInputStream fis = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetname);

            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetname);
            }
            
            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();

            data = new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {

                XSSFRow row = sheet.getRow(i);

                for (int j = 0; j < cols; j++) {

                    if (row == null || row.getCell(j) == null) {
                        data[i - 1][j] = ""; // avoid null crash
                    } else {
                        data[i - 1][j] = row.getCell(j).toString();
                    }
                }
            }

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
	



    //  WRITE METHOD 
    public synchronized  static void setCellData(String sheetName, int rowNum, int colNum, String value) {

        String path = System.getProperty("user.dir") + "/TestData/testdata.xlsx";

        try {
            FileInputStream fis = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            // ✅ Get or create row
            XSSFRow row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            // ✅ Get or create cell
            XSSFCell cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
            }

            // ✅ Set value
            cell.setCellValue(value);

            // ✅ Write back to file
            FileOutputStream fos = new FileOutputStream(path);
            workbook.write(fos);

            // Close resources
            fos.close();
            workbook.close();

        } catch (Exception e) {
            System.out.println("Excel crashed:");
            e.printStackTrace();
    }
		
    }
}
