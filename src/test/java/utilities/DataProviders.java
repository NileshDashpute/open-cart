package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	//DataProvider 1
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{

		String path=".\\testData\\Opencart_LoginData.xlsx";      //taking excel file from testData
		ExcelUtility excelutil = new ExcelUtility(path);          //creating an object for excelUtility

		int totalrows = excelutil.getRowCount("Sheet1");	
		int totalcells= excelutil.getCellCount("Sheet1",1);

		String logindata[][]=new String[totalrows][totalcells];   //created for two dimension array which can store the data user and password

		for(int i=1;i<=totalrows;i++)  //1   //read the data from excel storing in two dimensional array
		{		
			for(int j=0;j<totalcells;j++)  //0    i is rows and j is column
			{
				logindata[i-1][j]= excelutil.getCellData("Sheet1",i, j);  //1,0
			}
		}
		return logindata;//returning two dimension array
	}

	//DataProvider 2
	@DataProvider(name = "LoginData2")
	public String[][] getExcelData() throws IOException
	{
		String path = ".\\testData\\OpenCart_LoginData.xlsx";
		ExcelUtility excelFile = new ExcelUtility(path);
		
		int totalRows = excelFile.getRowCount("Sheet2");
		int totalCells = excelFile.getCellCount("Sheet2", 1);
		
		String [][] loginData = new String[totalRows][totalCells];
		
		for (int i = 1; i <= totalRows ; i++) 
		{
			for (int j = 0; j < totalCells; j++) 
			{
				loginData[i-1][j] = excelFile.getCellData("Sheet2", i, j);
			}
		}
		return loginData;
	}
	
	//DataProvider 3
	//DataProvider 4
}
