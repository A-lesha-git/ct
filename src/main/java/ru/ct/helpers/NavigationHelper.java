package ru.ct.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import ru.ct.util.PropertyLoader;
import ru.ct.objects.NavBarPage;
import ru.ct.objects.User;
import ru.ct.fw.AppManager;
import ru.ct.fw.HelperWithWebDriverBase;



public class NavigationHelper extends HelperWithWebDriverBase{
	private final String ACCOUNTS = "/accounts/";
	private final  String SITES = "/sites";
	private final  String OPTIONS = "/personal";
	
	public  NavigationHelper(AppManager manager) {
		super(manager);
	}
	

	public void testAllPages(User user) throws IOException {
		manager.getAutorithationHelper().simpleLogin(user);
		testSiteListPage(user);
		testOtherPage(user);
		testLeftMenu(user);
		String url = manager.getPropsP().getProperty("url") + ACCOUNTS + user.getIdAccount();
		openUrl(url);
		manager.getAutorithationHelper().logout();
		
		
	}

	private void testOtherPage(User user) {
		String url = manager.getPropsP().getProperty("url") + ACCOUNTS + user.getIdAccount() + OPTIONS ;
		openUrl(url);
	}


	private void testLeftMenu(User user) throws IOException {
		ArrayList<NavBarPage> navBarPages =getLeftNavPageObjs(user);
		System.out.println(navBarPages);
		for (NavBarPage navBarPage : navBarPages) {
			String url = manager.getPropsP().getProperty("url") + ACCOUNTS +user.getIdAccount()+SITES+user.getSites()[0] + navBarPage.getHrefParams()+"begin_date_filter="+user.getStartDate()+"&end_date_filter="+ user.getEndDate();
			System.out.println("Перехожу в раздел "+ navBarPage.getName() + "  по урлу " +url);
			if(navBarPage.getTimeout()>30){
				System.out.println("Страница может долго грузится, максимальное ожидание установлено в  " + navBarPage.getTimeout() +" сек.");
			}
			openUrl(url);
			verifyNavBarRefs(navBarPage);
		}
	
	}


	

	private void verifyNavBarRefs(NavBarPage navBarPage) {
		if(navBarPage.getIsForbided()){
			// страница может быть недоступна для организации
			verifyHeaderOnPage(navBarPage,"h2");
		}else if(navBarPage.getIsFilterPage()){
			verifyHeaderOnPage(navBarPage,"h1");
		}else{
			verifyHeaderOnPage(navBarPage, "h1");
		}
		
	}


	private void verifyHeaderOnPage(NavBarPage navBarPage,String header) {
			try {
				webElementExistBy(findElement(By.tagName(header)), navBarPage.getTimeout());
				String headertxt = findElement(By.tagName(header)).getText();
				System.out.println("Переход на страницу осуществлен. Текст хэдера: "+header+ " " +headertxt);
			} catch (NoSuchElementException e) {
				System.out.println("Запрашиваемого тэга на странице не оказалось, проверяем что страница загрузилась");
				String footerTxt = findElement(By.className("part1")).getText();
				System.out.println("Футер загружен, текст  футера: " + footerTxt);
				//e.printStackTrace();
			}
		
	}

	private ArrayList<NavBarPage> getLeftNavPageObjs(User user) throws IOException {		
		//переходит на сайт что бы собрать урлы по которым в дальнейшем будем переходить и искать 500
//		goToSite(user.getName());
//		//открываем урл, с хэш параметрами
//		System.out.println(getCurrentUrl()+user.getSites()[0]+"statistics?selectSiteSegment=any&begin_date_filter=" + user.getStartDate() + "&end_date_filter=" + user.getEndDate());
//		openUrl(getCurrentUrl()+user.getSites()[0]+"statistics?selectSiteSegment=any&begin_date_filter=" + user.getStartDate() + "&end_date_filter=" + user.getEndDate());
//		//openTabs();
//		WebElement sidebarEl = findElement(By.className("Sidebar"));
//		List<WebElement> hrefs =sidebarEl.findElements(By.tagName("a"));
//		for (WebElement href : hrefs) {
//			String name  = href.getText();
//			String source = href.getAttribute("href");
//			System.out.println(name + ";" + source);
//		}
		
		ArrayList<NavBarPage> navBarPages = new ArrayList<NavBarPage>();
		File file = new File("test-data/pages-data/nav-refs");
		BufferedReader br = new BufferedReader(
				   new InputStreamReader(
		                      new FileInputStream(file), "UTF8"));
		String line = br.readLine();
		while(line != null){
			NavBarPage navBarPage = new NavBarPage();
			System.out.println(line);
			String parts[] = line.split(";");
			//зная примерное время загр. страниц задаем значение возможного ожидания
			int timeout = Integer.parseInt(parts[2]);
			//переменные по которым можно определять страница может быть недоступна, или страница является фильтром по какому-то параметру "трафик по браузерам" и т.д.
			boolean isForbiden = Boolean.parseBoolean(parts[3]);
			boolean isFilterPage = Boolean.parseBoolean(parts[4]);
			
			navBarPages.add(navBarPage
					.setName(parts[0])
					.setHrefParams(parts[1])
					.setTimeout(timeout)
					.setIsForbided(isForbiden)
					.setIsFilterPage(isFilterPage)
			);
			line = br.readLine();
		}
		br.close();
		return navBarPages;
	}





	private void goToSite(String name) {
		List<WebElement> sites = findElements(By.className("SiteName"));
		System.out.println(sites.size());
		if(sites.size()>1){	 
			for (WebElement el : sites) {
				String siteName = el.getText();
				//System.out.println(siteName);
				if(name.indexOf(siteName) != -1) {
					System.out.println("Переходим на сайт " + siteName);
					el.click();
					break;
				}
			}
		}else{
			throw new java.lang.AssertionError("У сайта только один сайт демо, который не подходит для теста ");
		}
		
	}


	private void testSiteListPage(User user) {
		System.out.println("Проверяем страницу вывода списка сайтов организации " + getCurrentUrl());
		List<WebElement> siteNames = findElements(By.className("SiteName"));
		int numOfSitesOnPage = siteNames.size();
		System.out.println("У юзера: " + user.getLoginName() + " кол-во сайтов на странице: " + numOfSitesOnPage);
		//setDates();
	}





	private void setDates(String beginDate, String endDate) {
		// дата пикер косячит и сам проставляет какие-то левые даты, возможно тут баг
		findElement(By.id("end_date")).clear();
		findElement(By.id("end_date")).sendKeys(endDate);
		
		findElement(By.id("begin_date")).clear();
		findElement(By.id("begin_date")).sendKeys(beginDate);
		
		findElement(By.className("btn")).click();
	}





	private void closePopUpInfo() {
		try {
			webElementExistBy(findElement(By.className("popup_m_viewer-ui-close")), 2);
			findElement(By.className("popup_m_viewer-ui-close")).click();
		} catch (NoSuchElementException e) {
			
		}
		
	}



}
