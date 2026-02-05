package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\LoginTest_InputData.xlsx";
		
		ExcelUtils xl=new ExcelUtils(path);
		int rowcount=xl.getRowCount("InputData");
		int colcount=xl.getCellCount("InputData", 1);
		
		String logindata[][]=new String[rowcount][colcount];
		for(int i=1;i<=rowcount;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=xl.getCellData("InputData", i, j);
			}
		}
		return logindata;
	}

}
