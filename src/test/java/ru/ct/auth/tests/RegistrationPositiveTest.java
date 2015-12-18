package ru.ct.auth.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import ru.ct.dp.DataGeneratorAuth;
import ru.ct.fw.TestBase;
import ru.ct.objects.User;

public class RegistrationPositiveTest extends TestBase{
	 @Test(dataProvider="positiveRegistrationDataFromFile", dataProviderClass = DataGeneratorAuth.class, groups = {"autorization"})
	public void negativeRegistrationTests(User positiveUser) throws IOException {							
		 System.out.println("");
		 app.getAutorithationHelper().positiveRegistrationTest(positiveUser);
	 }
	 
//	@Test
//	public void positiveRegistrationTests() throws IOException {
//		User positiveUser = new User()
//									.setName("negative name")
//									.setEmail("mail@ru.ru")
//									.setPhoneMob("1234567890")
//									.setSite("asdfkldfaskljdas.ru");
//		app.getAutorithationHelper().positiveRegistrationTest(positiveUser);
//		
//	}
}
