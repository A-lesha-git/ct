package ru.ct.auth.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import ru.ct.fw.TestBase;
import ru.ct.objects.User;

public class AuthTest extends TestBase{
	@Test
	public void authorization() throws IOException {
		User user = new User().setLoginName(app.getPropsF().getProperty("admin", null)).setPSW(app.getPropsF().getProperty("psw", null)).setType(1+"");
		app.getAutorithationHelper().loginTest(user);
		//app.getDataProp("type");
		//app.getNavigationHelper().openAnyUrl(app.getProps().getProperty("lkcalltouch", null));
}
}
