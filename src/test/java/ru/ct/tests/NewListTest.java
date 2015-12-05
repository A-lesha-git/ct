package ru.ct.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import ru.ct.dp.DataGeneratorAuth;
import ru.ct.dp.DataGeneratorPages;
import ru.ct.fw.TestBase;
import ru.ct.objects.User;

public class NewListTest extends TestBase{
	 @Test(dataProvider="authDataFromFile", dataProviderClass = DataGeneratorAuth.class)
	  public void testAuthGenerator(User userUnit) throws Exception {
		 app.getAutorithationHelper().loginTest(userUnit);
	  }
	 
	 @Test(dataProvider="authDataPagesFromFile", dataProviderClass = DataGeneratorPages.class)
	 public void find500ErrorsOnPagesTest(User user) throws IOException{
			app.getNavigationHelper().testAllPages(user);
	 }
}
