package qtriptest;

import org.testng.annotations.DataProvider;

public class DP {

    @DataProvider(name = "TestCase1")
	public static Object[][] getData() 
	{
		Object[][] arr = ExcelReader.getDataFromSheet("TestCase01");
		return arr;
	}

	@DataProvider(name = "TestCase2")
	public static Object[][] getDataFrom() 
	{
		Object[][] arr = ExcelReader.getDataFromSheet("TestCase02");
		return arr;
	}

	@DataProvider(name = "TestCase3")
	public static Object[][] getDataFromsheet3() 
	{
		Object[][] arr = ExcelReader.getDataFromSheet("TestCase03");
		return arr;
	}


	@DataProvider(name = "TestCase4")
	public static Object[][] getDataFromsheet4() 
	{
		Object[][] arr = ExcelReader.getDataFromSheet("TestCase04");
		return arr;
	}



    
}
