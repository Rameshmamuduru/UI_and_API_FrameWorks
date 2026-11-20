
package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    // DataProvider 1 - Login Data

    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        String path = ".\\test-data\\Opencart_TestData.xlsx";

        ExcelUtility xlutil = new ExcelUtility(path);

        int totalrows = xlutil.getRowCount("logintestdata");
        int totalcols = xlutil.getCellCount("logintestdata", 0);

        // Store valid test data rows
        String logindata[][]=new String[totalrows][totalcols];

        // Start from row 1 because row 0 contains column headers
        for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				logindata[i-1][j]= xlutil.getCellData("logintestdata",i, j);  //1,0
			}
		}
	return logindata;//returning two dimension array
    }

}
	