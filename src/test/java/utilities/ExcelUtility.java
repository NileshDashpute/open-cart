package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public XSSFCellStyle style;
	String path;

	public ExcelUtility(String path) 
	{
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException 
	{
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		
		int rowCount = sheet.getLastRowNum();
		
		workbook.close();
		fis.close();
		return rowCount;
	}

	public int getCellCount(String sheetName, int rowNum) throws IOException 
	{
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		
		int cellCount = row.getLastCellNum();
		
		workbook.close();
		fis.close();
		return cellCount;
	}

	public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException 
	{
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		
		DataFormatter formatter = new DataFormatter();
		String data = formatter.formatCellValue(cell)
				;//Returns the formatted value of cell as a string type.
		workbook.close();
		fis.close();
		return data;
	}

	public void setCellData(String sheetName, int rowNum, int cellNum, String data) throws IOException 
	{
		File excelFile = new File(path);

		if (!excelFile.exists())  // if excel file does not exist
		{
			fos = new FileOutputStream(path);
			workbook = new XSSFWorkbook();
			workbook.write(fos);
		}

		if(workbook.getSheetIndex(sheetName)==-1) // if sheet does not exist
		{
			workbook.createSheet(sheetName);
		}

		if(sheet.getRow(rowNum)==null) // if row does not exist
		{
			row = sheet.createRow(rowNum);
		}

		cell = row.createCell(cellNum);
		cell.setCellValue(data);

		workbook.write(fos);
		workbook.close();
		fos.close();
	}

	public void fillGreenColor(String sheetName,int rownum,int colnum) throws IOException
	{

		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);

		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
		cell.setCellStyle(style);

		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}

	public void fillRedColor(String sheetName,int rownum,int colnum) throws IOException
	{
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);

		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		cell.setCellStyle(style);	

		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}



}
