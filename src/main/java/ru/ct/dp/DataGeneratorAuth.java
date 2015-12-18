package ru.ct.dp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.testng.annotations.DataProvider;
import ru.ct.objects.User;

public class DataGeneratorAuth {
	public final static String keyEmail = "NtCnJdJtVsk@";// по данному ключу, в дальнейшем из базы можно будет вытащить тестовые аккаунты
	public final static int keyPhone = 71230;// по данному ключу, в дальнейшем из базы можно будет вытащить тестовые аккаунты
	public final static int numOfValidUsersToGenerate = 3;// количество валидных аккаунтов, которое нужно сгенерировать
	@DataProvider(name="authDataFromFile")
	public static Iterator<Object[]> loadAuthHelperFromFile() throws IOException{
		System.out.println("/* \n *Начинается выполнения тестов(testAuthGenerator). \n *Тест проверяет работу авторизации"
				+ "\nлогинясь под разными типами аккаунтов*\n*/");
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
				User userUnit = new User();
				userUnit
					.setLoginName(lineparts[0]).setType(lineparts[1]).setPSW("kuyoh1Xie4Aif8A");
				list.add(new Object[]{userUnit});
			}
			line = br.readLine();
			br.close();
		}
		return list.iterator();
	}
	@DataProvider(name="negativeRegistrationDataFromFile")
	public static Iterator<Object[]> loadNegativeRegHelperFromFile() throws IOException{
		System.out.println("/* \n *Начинается выполнения тестов(negativeRegistrationTests). \n *Тест проверяет работу функционала регистрации"
				+ "\nПроверим что нельзя зарегистрироваться используя невалидные данные*\n*/");
				List<Object[]> list = new ArrayList<Object[]>();
				File file = new File("test-data/auth-data/negative-registration");
				BufferedReader br = new BufferedReader(new InputStreamReader(
		                new FileInputStream(file), "UTF8"));
				
				 
				String line = br.readLine();
				while(line != null){
					//System.out.println(line);
					int num = line.indexOf("для справки:");
					if(line.indexOf("для справки:") == -1){
						String[] lineparts = line.split(";");
						String name = lineparts[0];
						String email = lineparts[1];
						String phone = lineparts[2];
						String site = lineparts[3];				
						User userUnit = new User();
						userUnit
							.setName(name)
							.setEmail(email)
							.setPhoneMob(phone)
							.setSite(site);
						list.add(new Object[]{userUnit});
					}
					line = br.readLine();
				}
				br.close();
				return list.iterator();
	}
	
	@DataProvider(name="positiveRegistrationDataFromFile")
	public static Iterator<Object[]> loadPositiveRegHelperFromFile() throws IOException{
		System.out.println("/* \n *Начинается выполнения тестов(positiveRegistrationTests). \n *Тест проверяет работу функционала регистрации"
				+ "\nПроверим что можно зарегистрировать аккаунт, используя валидные сгенерированные данные*\n*/");
				List<Object[]> list = new ArrayList<Object[]>();
				
				for(int j=0; j<numOfValidUsersToGenerate;j++){
					User validUser = new User();
					String name = generateValidName();
					String email = generateValidEmail();
					String phone = generateValidPhone();
					String site = generateValidSite();
					validUser
							.setName(name)
							.setEmail(email)
							.setPhoneMob(phone)
							.setSite(site);
					list.add(new Object[]{validUser});
				}
				
				//ArrayList<User> validUsers = generateValidUser(numOfValidUsersToGenerate);
				return list.iterator();
	}

//	private static ArrayList<User>  generateValidUser(int i) {
//		//в данном методе генерируются объкты  валидных юзеров 
//		ArrayList<User> validUsers = new ArrayList<User>();
//		for(int j=0; j<i;i++){
//			User validUser = new User();
//			String name = generateValidName();
//			String email = generateValidEmail();
//			String phone = generateValidPhone();
//			String site = generateValidSite();
//			validUser
//					.setName(name)
//					.setEmail(email)
//					.setPhoneMob(phone)
//					.setSite(site);
//			validUsers.add(validUser);
//			
//		}
//		return validUsers;
//	}

	private static String generateValidSite() {
		//метод генерит валидное название сайта
		//название может быть либо англ либо русское с вероятностью 50%
		// длина от 4 до 20 символов.
		int lengthOfNameSite = generateNumFromTo(4,21);
		String site = "";
		Random rnd  = new Random();
		int rndNum = rnd.nextInt(2);
		if(rndNum==0){
			site += generateRndSymbols("latin", lengthOfNameSite)+".";
			site += generateCountry("eng");	
		}
		if(rndNum==1){
			site += generateRndSymbols("рус", lengthOfNameSite)+".";
			site += generateCountry("рус");
		}
		return site;
	}



	private static String generateValidPhone() {
		// генерация телефона
		// первые 4 цифры являются ключом 7123
		String phone = keyPhone + "";
		phone += generateRndSymbols("num", 5);
		return phone;
	}

	private static String generateValidEmail() {
		String email = keyEmail;
		int lengthOfPartTwo = generateNumFromTo(3, 10);  // длинна второй части email от 3 до 10 символов
		email += generateRndSymbols("latin", lengthOfPartTwo)+".";
		email  += generateCountry("eng");
		return email;
	}

	private static String generateCountry(String key) {
		String country = "";
		int rndNum = 0;
		Random rnd = new Random();
		switch (key) {
		case "eng":
			String[] countries = {"su","ru","com", "ua","org", "net", "eu","kz"};
			rndNum = rnd.nextInt(countries.length);
			country = countries[rndNum];
			break;
		case "рус":
			String[] countryRu = {"рф"};//{"ру", "рф", "москва", "россия"};
			rndNum = rnd.nextInt(countryRu.length);
			country = countryRu[rndNum];
			break;
		default:
			break;
		}
		return country;
	}

	private static String generateValidName() {
		// генерируем валидное имя
		// границы от 3 до 255
		// символы любые
		int lengthOfName = generateNumFromTo(3,255); // выкидываем случайное число от 3 до 255
		String str =  generateRndSymbols("any", lengthOfName);
		return str;
	}


	private  static String generateRndSymbols(String key, int lengthOfString) {
		//метод принимает ключ и длину строки которую нужно сгенерить
		TreeMap<String,String> dicts = new TreeMap<String,String>();
		dicts.put("рус", "абвгдеёжзийклмнопрстуфхцчъыьэюяАБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧЪЫЬЭЮЯ");
		dicts.put("latin", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		dicts.put("num", "0123456789");
		dicts.put("special", "~!@#$%^&*()_+-=");
		Random rnd = new Random();
		String string = "";
		switch (key) {
		case "any": //означает что генерится строка из любых символов
			for(int i=0; i<lengthOfString;i++){
				//выбрасываем случайное число от 0 до 3, которое и будет ключом 0-рус, 1-latin, 2-num, 3-special
				int rndNum = rnd.nextInt(4);
				if(rndNum == 0){
					String str = dicts.get("рус");
					string += generateString(str);
				}else if(rndNum==1){
					String str = dicts.get("latin");
					string += generateString(str);
				}else if(rndNum==2){
					String str = dicts.get("num");
					string += generateString(str);
				}
				else if(rndNum==3){
					String str = dicts.get("special");
					string += generateString(str);
				}
			}
			break;
			
		case "latin":// генерится строка из latin символов
			String str = dicts.get("latin");
			for (int i = 0; i < lengthOfString; i++) {
				string += generateString(str);
			}
			string += generateString(str);
			break;
		case "num"://генерится строка из цифр
			String numStr = dicts.get("num");
			for (int i = 0; i < lengthOfString; i++) {
				string += generateString(numStr);
			}
			break;
		case "рус"://генерится строка из русских букв
			String rusStr = dicts.get("рус");
			for (int i = 0; i < lengthOfString; i++) {
				string += generateString(rusStr);
			}
			break;
		default:
			break;
		}	
		return string;
	}

	private static String generateString(String str) {
		// расскаладывает алфавит или цифры(от 0 до 9) в массив, где буква или цифра является элементом массива
		Random rnd = new Random();
		char[] chars = str.toCharArray();
		int length = chars.length;
		String string = "";
		return chars[rnd.nextInt(length)]+"";
	}
	private static int generateNumFromTo(int from, int to) {
		//метод генерит длину строки в определенном диапазоне
		int lengthOfPartTwo = (int) (Math.random()*to+from);
		return lengthOfPartTwo;
	}
}
