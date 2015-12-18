package ru.ct.auth.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import ru.ct.fw.TestBase;

public class SimpleTest extends TestBase{
	@Test
	public void simpleTest() throws IOException {
		//app.getAutorithationHelper().loginTest();
//		app.getDataProp("url");
		//app.getNavigationHelper().openAnyUrl(app.getProps().getProperty("lkcalltouch", null));
		app.getNavigationHelper().openMainPage();
		app.getNavigationHelper().simpleVerifyThatAuthorizationPage();
	}
}
