package ru.ct.fw;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.ct.fw.AppManager;

public class HelperWithWebDriverBase {
	protected final AppManager manager;
	private static WebDriver driver;
	String originalWindow;
	String newWindow;
	public static Random rnd = new Random();
	public  HelperWithWebDriverBase(AppManager manager) {
		this.manager = manager;
		driver = manager.getWebDriverHelper().getDriver();
	}
	

	public int getSimpleRnd(int length){
		Random rnd = new Random();
		return rnd.nextInt(length);
	}
	
	public void makeScreenshot() throws IOException{
		File screenshot = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
		String path = "c:/test/screenshots/minicard" + screenshot.getName();
		FileUtils.copyFile(screenshot, new File(path)); 
	}
	
	public void makeScreenshotMiniCards(int id) throws IOException{
		File screenshot = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
		String path = "c:/test/screenshots/minicard" + screenshot.getName();
		FileUtils.copyFile(screenshot, new File(path)); 
	}


	public boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	protected WebElement findElement(By linkText) {
		return driver.findElement(linkText);
	}
	
	protected List<WebElement> findElements(By linkText) {
		return  driver.findElements(linkText);
	}

	protected void openUrl(String string) {
		driver.get(string);
	}

	public void openMainPage() {
		// ������� ������� ��������  
		openUrl(manager.getMainUrl());
		
	}
	
	public String getBaseUrl(){
		String baseUrl = manager.getMainUrl();
		return baseUrl;
	}
	
//	public String  getLkCalltouchUrl() {
//		//String lkCalltouchUrl =  manager
//	}
	
	
	public void doubleClick(WebElement webElement) {
		new Actions(driver).moveToElement(webElement).doubleClick().perform(); 
	}
	
	public void type(By locator, String text) {
		clickAndClear(locator).sendKeys(text);
	}
	
	public WebElement click(By locator) {
		WebElement element = findElement(locator);
		element.click();
		return element;
	}
	
	
	public void  clickBycords(By locator, WebElement el) {
		
		
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(locator));
		//builder.perform();
		builder.build().perform();
		System.out.println("Hovering on the element ! " + el.isDisplayed());
	
	}
	
	public void switchToFrame(String id){
		 driver.switchTo().frame(id);	 
	}
	
	public void defaultContent(){
		driver.switchTo().defaultContent();
	}
	
	public WebElement clear(By locator) {
		WebElement element = findElement(locator);
		element.clear();
		return element;
	}
	
	public WebElement clickAndClear(By locator) {
		clear(locator);
		return click(locator);
	}
	
	public void alert() {
		driver.switchTo().alert(); 
	}
	
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
//	public static <T> void assertThat(T actual, Matcher<T> matcher) {
//		assertThat("", actual, matcher);
//	}
//	
//	private static <T> void assertThat(String reason, T actual, Matcher<T> matcher) {
//		if (!matcher.matches(actual)) {
//			Description description = new StringDescription();
//			description.appendText(reason)
//				.appendText("\nExpected: ")
//				.appendDescriptionOf(matcher)
//				.appendText("\n     got: ")
//				.appendValue(actual)
//				.appendText("\n");
//				System.out.println("		!Assertion failed:" + description.toString());
//			throw new java.lang.AssertionError(description.toString());
//		}
//	}
	
	public void waitForInvisibilityElement(By locator, int timeOut) {
		(new WebDriverWait(driver, timeOut)).until(ExpectedConditions.invisibilityOfElementLocated(locator));      
	}
	
	public boolean elementIsEmpty(By locator) {
		if(!driver.findElements(locator).isEmpty()){
				return true;	
		}
		return false;
	}
	
	public static boolean elementIsVisible(By by) {
		try {
			driver.findElement(by).isDisplayed();
			return true;
		}catch(Exception ignored) {
			return false;
		}
	}
	
	public static boolean elementExistBy(By by, int timeout) {
		try {
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
			driver.findElement(by).isDisplayed();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;
		}catch(Exception ignored) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return false;
		}
	}
	public static boolean webElementExistBy(WebElement element, double d) {
		try {
			driver.manage().timeouts().implicitlyWait((long) d, TimeUnit.SECONDS);
			element.isDisplayed();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;
		}catch(Exception ignored) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return false;
		}
	}
	
	
	public static boolean webElementExistBy(WebElement element, long timeout) {
		try {
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.MILLISECONDS);
			element.isDisplayed();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
			return true;
		}catch(Exception ignored) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
			return false;
		}
	}
	public void waitForTextToBePresentInElement(WebElement element, int timeOut, String text) {
		(new WebDriverWait(driver, timeOut)).until(ExpectedConditions.textToBePresentInElement(element, text));      
	}
	
	public boolean waitForVisibilityElement(By locator, int timeout) {
		try {
			new WebDriverWait(driver, timeout, 500).until(ExpectedConditions.visibilityOfElementLocated(locator));
			   return true;
		  }catch (org.openqa.selenium.TimeoutException ignored){
			 return false;
		  }
	}

	
	public Select selector(WebElement webElement) {
		Select selectElement = new Select(webElement);
		return selectElement;
	}
	
	public static boolean elementIsEnabled(By by, int timeout) {
		try {
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
			driver.findElement(by).isEnabled();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;
		}catch(NoSuchElementException ignored) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return false;
		}
	}
	
	public static boolean webElementEnabledBy(WebElement element, int timeout) {
		try {
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
			element.isEnabled();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;
		}catch(Exception ignored) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return false;
		}
	}
	
	

	
	public static Object getSetElements(Set<?> object, int get) {
		return object.toArray()[get];
	}
	

	public void waitForElementPresent(By locator, int timeout) {
		  new WebDriverWait(driver, timeout, 500).until(presenceOfElementLocated(locator));
		  new WebDriverWait(driver, timeout, 500).until(visibilityOf(driver.findElement(locator)));
	}
	
	public WebElement elementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}
	
	//�������� �� ��������� ��������/������������ � ������
	public WebElement waitVisibilityWebElement(WebElement elem, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(elem));
		return element;
	}
	
	//������������ �� ��������� �������� (���� �� ��������� �� ��������� ������� ������� - ������� � ����)
	protected WebElement moveTo(WebElement where) {
		Actions builder = new Actions(driver); 
		builder.moveToElement(where).perform();
		return where;
	}
	
	public boolean sleep(int timeout) {
		try {
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
			findElement(By.id("sleepsleep")).isDisplayed();
			return true;
		}catch(NoSuchElementException ignored) {
			return false;
		}catch(IndexOutOfBoundsException ignored) {
			return false;
		}
	}
	public boolean sleepMili(int timeout) {
		try {
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.MILLISECONDS);
			findElement(By.id("sleepsleep")).isDisplayed();
			return true;
		}catch(NoSuchElementException ignored) {
			return false;
		}catch(IndexOutOfBoundsException ignored) {
			return false;
		}
	}
	
	//��������� ������������
	public void typeWithAutocomplite(String there, WebElement webElement) {
		webElement.clear();
		webElement.sendKeys(there);
		sleep(2);
		String tab = Keys.chord(Keys.TAB);
		webElement.sendKeys(tab);
	}

	public void pressKey(int d, Keys key) {
		for (int i = 0; i < d; i++ ) {
			String pressBoth = Keys.chord(key);
			driver.findElement(By.id("pageContent")).sendKeys(pressBoth);
		}
	}
	
	public void pressCtrlF5(){
		String pressF5 = Keys.chord(Keys.CONTROL, Keys.F5);
		findElement(By.tagName("body")).sendKeys(pressF5);
	}
	public void pressEnterMainSearch(WebElement e){
		String pressEnter = Keys.chord(Keys.ENTER);
		e.sendKeys(pressEnter);
	}

	
	public void pressEnterLogin(WebElement e){
		String pressEnter = Keys.chord(Keys.ENTER);
		e.sendKeys(pressEnter);
	}
	public void pressPgDown(){
		String pgDown = Keys.chord(Keys.PAGE_DOWN);
		findElement(By.tagName("body")).sendKeys(pgDown);
	}
	public void pressF11(){
		String pressF11 = Keys.chord(Keys.F11);
		findElement(By.tagName("body")).sendKeys(pressF11);
	}
	public void pressDown(int i) {
		for (int j = 0; j < i; j++) {
			String pressDown = Keys.chord(Keys.DOWN);
			findElement(By.tagName("body")).sendKeys(pressDown);
			sleepMili(300);
		}
		
	}
	public void pressPgUpLimit() {
		for (int j = 0; j < 5; j++) {
			String pressDown = Keys.chord(Keys.PAGE_UP);
			findElement(By.tagName("body")).sendKeys(pressDown);
		}
		
	}
	
	//������� ��� � ����� ����
	public void openInNewWindow(String url) {
	    ((JavascriptExecutor) driver)
	                 .executeScript("window.open(arguments[1])");
	    driver.get(url);
	}
	
	//��������� ��� � ����� ���� � ����������� ������� �� �������� ����
	public void switchToNewWindow(String url) {
        originalWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        openInNewWindow(url);
        newWindow = (new WebDriverWait(driver, 10))
            .until(new ExpectedCondition<String>() {
                public String apply(WebDriver driver) {
                    Set<String> newWindowsSet = driver.getWindowHandles();
                    newWindowsSet.removeAll(oldWindowsSet);
                    return newWindowsSet.size() > 0 ?
                                 newWindowsSet.iterator().next() : null;
                  }
                }
            );
        driver.switchTo().window(newWindow);
	}
	
	//������� �������� ���� � ������������� � ������ ����
	public void closeWindowAndSwitchToOldWindow() {
		driver.close();
		driver.switchTo().window(originalWindow);
	}
	
	/*public void moveSlider(WebElement slider, int x, int y) {
		Actions actions = new Actions(driver);
		actions.clickAndHold(slider).moveByOffset(x, y).release().build();
		actions.clickAndHold(slider).moveByOffset(x, y).release().build();
		actions.perform();
	}*/
	
	public void moveSlider(WebElement slider, Integer xOffset,  Integer position){
	    Actions actions = new Actions(driver);
	    actions.dragAndDropBy(slider, xOffset, 0).perform();
//		  Actions actions = new Actions(driver);
//		  actions.clickAndHold(slider).moveByOffset(x, y).release().build();
//		  actions.perform();
		
	}
	
	public void onMouseOver(WebElement el) {
		Actions builder = new Actions(driver);
		Action hoverAction = builder.moveToElement(el).build();
		builder.moveToElement(el).build().perform();
		hoverAction.perform();
		sleep(2);
		
		
	}
	public void alertAccept(){
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public  String generateRndSymbols(String key, int lengthOfString) {
		TreeMap<String,String> dicts = new TreeMap<String,String>();
		dicts.put("���", "�����������������������������������Ũ�����������������������");
		dicts.put("latin", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		dicts.put("num", "0123456789");
		Random rnd = new Random();
		String string = "";
		if(!key.equals("any")){
			for(Map.Entry<String, String> dict:dicts.entrySet()){
				if(dict.getKey().equals(key)){
					String str = dict.getValue();
					string = generateString(lengthOfString, str,true);
					}
				}
		}else if(key.equals("any")){
			//����������� �� 0 �� 2,
			//�.�. ����������� ��������� ������� ����� 33%
			for (int i = 0; i < lengthOfString; i++) {
				int rndNum = rnd.nextInt(3);
				if(rndNum==0){
					String str = dicts.get("���");
					string+= generateString(lengthOfString, str,false);
				}else if(rndNum==1){
					String str = dicts.get("latin");
					string += generateString(lengthOfString, str,false);
				}else if(rndNum==2){
					String str = dicts.get("num");
					string += generateString(lengthOfString, str, false);
				}
			}	
		}
		
		return string;
	}
	
	public  String generateString(int lengthOfString, 
			 String str, boolean fullString) {
		Random rnd = new Random();
		char[] chars = str.toCharArray();
		int length = chars.length;
		String string="";
		if(fullString){
			for (int i = 0; i < lengthOfString; i++) {
				string += chars[rnd.nextInt(length)];
			}
			return string;
		}else{
			return chars[rnd.nextInt(length)]+"";
		}
	}
	
}
