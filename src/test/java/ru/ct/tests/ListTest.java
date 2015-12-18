package ru.ct.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import ru.ct.dp.DataGeneratorAuth;
import ru.ct.dp.DataGeneratorPages;
import ru.ct.fw.TestBase;
import ru.ct.objects.User;

public class ListTest extends TestBase{


	 
	 

	 
	 @Test(dataProvider="authDataPagesFromFile", dataProviderClass = DataGeneratorPages.class, priority = 3)
	 public void find500ErrorsOnPagesTest(User user) throws IOException{
			app.getNavigationHelper().testAllPages(user);
	 }
	 
	 @Test(dataProvider="authDataFromFile", dataProviderClass = DataGeneratorAuth.class, priority = 2)
		  public void testAuthGenerator(User userUnit) throws Exception {
			 app.getAutorithationHelper().loginTest(userUnit);
		  }
	 @Test(dataProvider="positiveRegistrationDataFromFile", dataProviderClass = DataGeneratorAuth.class, groups = {"autorization"}, priority = 1)
		public void positiveRegistrationTests(User positiveUser) throws IOException {							
			 System.out.println("");
			 app.getAutorithationHelper().positiveRegistrationTest(positiveUser);
		 }
	 @Test(dataProvider="negativeRegistrationDataFromFile", dataProviderClass = DataGeneratorAuth.class, groups = {"autorithation"}, priority = 0)
	 	public void negativeRegistrationTests(User negativeUser) throws IOException {
		 app.getAutorithationHelper().negativeRegistrationTest(negativeUser);
	 }
}
