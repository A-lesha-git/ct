package ru.ct.dp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.DataProvider;
import ru.ct.objects.User;

public class DataGeneratorPages {
	@DataProvider(name="authDataPagesFromFile")
	public static Iterator<Object[]> loadAuthHelperFromFile() throws IOException{
		System.out.println("/* \n *Начинается выполнения тестов(find500ErrorsOnPagesTest). \n *Тесты ищут 500 ошибки на странице"
				+ "\nтест производит переходпод под разными аккаунтами, по всем страницам в лк аккаунта*\n*/");
		//для справки: 0-наимен. запросa; 1...n результаты выдачи
		List<Object[]> list = new ArrayList<Object[]>();
		File file = new File("test-data/pages-data/test-page-data");
		BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "UTF8"));
		
		 
		String line = br.readLine();
		while(line != null){
			//System.out.println(line);
			int num = line.indexOf("для справки:");
			if(line.indexOf("для справки:") == -1){
				String[] lineparts = line.split(";");
//				String[] authResults = new String[lineparts.length-1];
//				for (int i = 0; i < lineparts.length-1; i++) {
//					authResults[i] = lineparts[i+1];
//					line = br.readLine();
//				}
				int idAccount = Integer.parseInt(lineparts[0]);
				String name = lineparts[1];
				String loginName = lineparts[2];
				String psw = lineparts[3];
				int type = Integer.parseInt(lineparts[4]);
				String[] sites = lineparts[5].split(",");
				String startDate = lineparts[6];
				String endDate = lineparts[7];
				User userUnit = new User();
				userUnit
					.setIdAccount(idAccount)
					.setName(name)
					.setLoginName(loginName)
					.setPSW(psw)
					.setType(type+"")
					.setSites(sites)
					.setStartDate(startDate)
					.setEndDate(endDate);
				list.add(new Object[]{userUnit});
			}
			line = br.readLine();
			
		}
		br.close();
		return list.iterator();
	}
}
