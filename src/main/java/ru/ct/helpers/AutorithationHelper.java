package ru.ct.helpers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import ru.ct.fw.AppManager;
import ru.ct.fw.HelperWithWebDriverBase;
import ru.ct.objects.User;

public class AutorithationHelper extends HelperWithWebDriverBase {

	public AutorithationHelper(AppManager manager) {
		super(manager);
		
	}

	public void generateTestAccount() {
		//метод генерит данные 
		
	}
	
	public void simpleLogin(User user){
		//метод производит логин, без проверок для простоты и быстроты
		openUrl(manager.getPropsP().getProperty("url"));
		fillAuthForm(user.getLoginName(),user.getPSW());
		pressBtnInn();
		verifyAuth(user.getType(), user.getLoginName());
		
	}
	public void loginTest(User user) {
		//метод производит тестовый логин
		openUrl(manager.getPropsP().getProperty("url"));
		veryfyThatLoginPage();
		fillAuthForm(user.getLoginName(),user.getPSW());
		pressBtnInn();
		verifyAuth(user.getType(), user.getLoginName());
		logout();
		//sleep(10);	
	}


	public void logout() {
		//Разлогиниться
		try {
			findElement(By.id("user-actions-menu")).click();
		} catch (NoSuchElementException e) {
			//дело в том что идентификатор у выпадающего меню на странице списка сайтов и других разный и отличается одной буквой s!!! 
			findElement(By.id("user-action-menu")).click();
		}
		
		sleep(2);		
		WebElement logout = findElement(By.linkText("Выход"));
		logout.click();
		veryfyThatLogout();
	}



	

	private void pressBtnInn() {
		//System.out.println("Нажимаю");
		findElement(By.cssSelector("input[class='mdl-button mdl-js-button mdl-button--raised  mdl-js-ripple-effect mdl-button--accent']")).click();
	}

	private void fillAuthForm(String name, String psw) {
		//заполняем форму авторизации
		findElement(By.id("j_username")).click();
		findElement(By.id("j_username")).clear();
		findElement(By.id("j_username")).sendKeys(name);;
		
		findElement(By.id("j_password")).click();
		findElement(By.id("j_password")).clear();
		findElement(By.id("j_password")).sendKeys(psw);
	}
	
	private void verifyAuth(String key, String loginName) {
		//Проверим что залогинились под определенным типом аккаунта(админ, агент или организация)
		String loginNameFromSite = "";
		String WeKnowPlaceHolderTxt = "Введите имя сайта или аккаунта или номер лицевого счета";
		String PlaceHolderTxt = "";
		String weKnowh1 = "Список организаций";
		switch (key) {
		case "admin":
			// Сперва проверим что имя аккаунта на сайте совпадает с тем под которым логинились
			loginNameFromSite = findElement(By.id("user-actions-menu")).getText();
			if(loginNameFromSite.indexOf(loginName) != -1){
				System.out.println("мы залогинены под админом: " + loginName);
			}else {
				throw new java.lang.AssertionError("мы НЕ залогинены под админом: " + loginName);
			}
			
			String h1txt = findElement(By.tagName("h1")).getText();
			if(h1txt.indexOf(weKnowh1) != -1){
				System.out.println("h1 загрузился и текст в нем совпадает с ожидаемым " + weKnowh1);
				
			}else {
				throw new java.lang.AssertionError("h1 текст не совпадает с ожидаемым");
			}
//			Данная проверка очень нестабильная часто падает в зависимости от скорости загрузки
//			PlaceHolderTxt = findElement(By.cssSelector("input[class='form-control ng-pristine ng-valid ng-touched']")).getAttribute("placeholder");
//			if(PlaceHolderTxt.toLowerCase().indexOf(WeKnowPlaceHolderTxt.toLowerCase()) != -1){
//				System.out.println("Авторизация произошка под: " + key);
//			}else {
//				throw new java.lang.AssertionError(" Авторизация не произошла под " + key + " текст  тега h1 " + PlaceHolderTxt);
//			}
			break;
		case "organization":
			loginNameFromSite = findElement(By.id("user-actions-menu")).getText();
			String [] partsFromSite = loginNameFromSite.split(" ");
			String [] parts = loginName.split("#");
			if(parts[0].indexOf(partsFromSite[0]) != -1){
				System.out.println("Залогинился под организацией : " + loginName);
			}else {
				throw new java.lang.AssertionError("мы НЕ залогинены под организацией: " + loginName);
			}
			List<WebElement> sitesBlock = findElements(By.className("SiteCard_header"));
			if(webElementExistBy(sitesBlock.get(0), 10)){
				System.out.println("На странице списка сайтов - " + sitesBlock.size() + " сайтов");
//				for (WebElement el : sitesBlock) {
//					String headerOfSiteBlock = el.findElement(By.className("SiteName")).getText();
//					if(headerOfSiteBlock.indexOf(parts[0]) != -1){
//						System.out.println("В списке сайтов, найден один из сайтов: " + headerOfSiteBlock);
//						break;
//					}else{
//						// бывает что название аккаунта не совпадает с названием сайта
//						System.out.println("Первая карточка в списке: "+headerOfSiteBlock );
//						break;
//					}
//				}
			}else {
				throw new java.lang.AssertionError("Не удалось залогиниться под организацией");
			}
			
			break;
			
		case"agent":
			loginNameFromSite = findElement(By.id("user-actions-menu")).getText();
			String [] partsOfAccauntOne= loginNameFromSite.split(" ");
			String [] partsOfAccauntTwo= loginName.split("#"); 
			if(partsOfAccauntTwo[0].indexOf(partsOfAccauntOne[0]) != -1){
				System.out.println("мы залогинены под агентом: " + loginName);
			}else {
				throw new java.lang.AssertionError("мы НЕ залогинены под: " + loginName);
			}
			
			String numOfOrganizationAgent  =  findElement(By.cssSelector("span[class='ngLabel ng-binding']")).getText();
			System.out.println(numOfOrganizationAgent);
			
			String h1txtAgentPage = findElement(By.tagName("h1")).getText();
			if(h1txtAgentPage.indexOf(weKnowh1) != -1){
				System.out.println("h1 загрузился и текст в нем совпадает с ожидаемым " + weKnowh1);
			}else {
				throw new java.lang.AssertionError("h1 текст не совпадает с ожидаемым");
			}
//			PlaceHolderTxt = findElement(By.cssSelector("input[class='form-control ng-pristine ng-valid ng-touched']")).getAttribute("placeholder");
//			if(PlaceHolderTxt.toLowerCase().indexOf(WeKnowPlaceHolderTxt.toLowerCase()) != -1){
//				System.out.println("Авторизация произошка под: " + key);
//			}else {
//				throw new java.lang.AssertionError(" Авторизация не произошла под " + key + " текст  тега h1 " + PlaceHolderTxt);
//			}
			break;
		default:
			break;
		}		
	}
	
	private void veryfyThatLoginPage() {
		//Проверка что мы находимся на страница авторизации
		WebElement loginConten = findElement(By.className("login_content"));
		if(!webElementExistBy(loginConten, 10)){
			logout();
		}
		if(webElementExistBy(loginConten, 10)){
			
		}else {
			throw new java.lang.AssertionError("мы не на страницы /login, см. в ручную что не так");
		}
		String h1txt = findElement(By.tagName("h1")).getText();
		String weKnowH1txt = "Сервис аналитики и управления интернет-рекламой";
		if(h1txt.indexOf(weKnowH1txt) != -1){
			System.out.println("Мы на странице авторизации");
		}else{
			throw new java.lang.AssertionError("это не страница авторизации, см. в ручную! ");
		}
	}
	


	private void veryfyThatLogout() {
		sleep(3);
		int x = 0;
		try {
			WebElement logContent = findElement(By.className("login_content"));
			x++;
			if(x>10){
				throw new java.lang.AssertionError("Не получается разлогиниться, см в ручную.");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Ждем еще 3 сек. пока произойдет разлогинивание");
			sleep(3);
			WebElement logContent = findElement(By.className("login_content"));
		}
//		WebElement loginContent = findElement(By.className("login_content"));
//		if(webElementEnabledBy( findElement(By.className("login_content")), 10)){
//			System.out.println("Разлогинились");
//			String h2txt = findElement(By.tagName("h2")).getText();
//			System.out.println(h2txt);
//		}
		
	}

}