package ru.ct.dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.DataProvider;
import ru.ct.objects.User;

public class DataGeneratorAuth {
	@DataProvider(name="authDataFromFile")
	public static Iterator<Object[]> loadAuthHelperFromFile() throws IOException{
		System.out.println("/* \n *Начинается выполнения тестов(testAuthGenerator). \n *Тест проверяет работу авторизации"
				+ "\nлогинясь под разными типами аккаунтов*\n*/");
		//для справки: 0-наимен. запросa; 1...n результаты выдачи
		List<Object[]> list = new ArrayList<Object[]>();
		BufferedReader br = new BufferedReader(new FileReader("test-data/auth-data/auth"));
		String line = br.readLine();
		while(line != null){
			//System.out.println(line);
			if(line.indexOf("для справки:") == -1 || line.indexOf("//") != -1){
				String[] lineparts = line.split(";");
				String[] authResults = new String[lineparts.length-1];
				for (int i = 0; i < lineparts.length-1; i++) {
					authResults[i] = lineparts[i+1];
				}
				//Search searchUnit = new Search();
				User userUnit = new User();
				userUnit
					.setLoginName(lineparts[0]).setType(lineparts[1]).setPSW("kuyoh1Xie4Aif8A");
				list.add(new Object[]{userUnit});
			}
			line = br.readLine();
		}
		return list.iterator();
	}
}
