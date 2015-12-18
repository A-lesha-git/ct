package ru.ct.pages.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import ru.ct.fw.TestBase;
import ru.ct.objects.User;

public class AllPagesTest extends TestBase{
	@Test
	public void allPageLoadingTest() throws IOException {
		String[] sites = {"/303/"};
		User user = new User()
								.setIdAccount(77)
								.setName("calltouch.ru")
								//.setLoginName(app.getPropsF().getProperty("calltouch", null))
								.setLoginName("calltouch#a.galvis")
								.setPSW("kuyoh1Xie4Aif8A")
								.setType(3+"").setSites(sites)
								.setStartDate("2015-10-07")
								.setEndDate("2015-10-09");
		app.getNavigationHelper().testAllPages(user);
	}
}
