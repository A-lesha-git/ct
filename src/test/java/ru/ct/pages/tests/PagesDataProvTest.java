package ru.ct.pages.tests;

import java.io.IOException;
import org.testng.annotations.Test;
import ru.ct.dp.DataGeneratorPages;
import ru.ct.fw.TestBase;
import ru.ct.objects.User;

public class PagesDataProvTest extends TestBase{
	 @Test(dataProvider="authDataPagesFromFile", dataProviderClass = DataGeneratorPages.class, groups = { "find500Errors" })
	 public void find500ErrorsOnPagesTest(User user) throws IOException{
			app.getNavigationHelper().testAllPages(user);
	 }
}
