package ru.ct.auth.tests;
import org.testng.annotations.Test;
import ru.ct.dp.DataGeneratorAuth;
import ru.ct.fw.TestBase;
import ru.ct.objects.User;

public class AuthDataProvTest extends TestBase{
//	@Test
//	public void authorization() throws IOException {
//		User user = new User().setLoginName(app.getProps().getProperty("admin", null)).setPSW(app.getProps().getProperty("psw", null)).setType(3+"");
//		app.getAutorithationHelper().loginTest(user);
//		//app.getDataProp("type");
//		//app.getNavigationHelper().openAnyUrl(app.getProps().getProperty("lkcalltouch", null));
//}
	
	 @Test(dataProvider="authDataFromFile", dataProviderClass = DataGeneratorAuth.class, groups = {"autorithation"})
	  public void testAuthGenerator(User userUnit) throws Exception {
		 app.getAutorithationHelper().loginTest(userUnit);
	  }
}
