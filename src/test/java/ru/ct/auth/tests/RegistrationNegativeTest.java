package ru.ct.auth.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import ru.ct.dp.DataGeneratorAuth;
import ru.ct.fw.TestBase;
import ru.ct.objects.User;

public class RegistrationNegativeTest extends TestBase{
	 @Test(dataProvider="negativeRegistrationDataFromFile", dataProviderClass = DataGeneratorAuth.class, groups = {"autorithation"})
	public void negativeRegistrationTests(User negativeUser) throws IOException {
//		User negativeUser = new User()
//									.setName("negative name")
//									.setEmail("Myалв@ыфываюылфва.ру")
//									.setPhoneMob("131242334")
//									.setSite("asdfkldfaskljdas");
									
		app.getAutorithationHelper().negativeRegistrationTest(negativeUser);
	 }
}
