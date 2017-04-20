package com.delhivery.utils;

import static com.delhivery.utils.AppLauncher.driver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidKeyCode;

public class Helper {

	int i = 0;
	static Boolean alreadyAllowedPermission = false;
	Object obj = null;

	
	String[][] json_KeyValuePair = new String[1000][2];

	String filePathScreenShot = "./src/main/resources/screenshots";
	private static String fileSeperator = System.getProperty("file.separator");
	private static String filePathConfig = "./src/main/resources/config/config.json";

	String parserTag = parseJSONToString("consoleLogParserTag", filePathConfig);
	public Boolean isNavigated = false;
	String oyoRoomsURL = parseJSONToString("oyoRoomsURL", filePathConfig), customURL;

	String execution_env = parseJSONToString("execution_environment", filePathConfig);

	public void openURL(boolean visit, String URL) {
		if (visit == true) {
			driver.get(URL);
		}
	}

	public void openURL() {
		driver.get(oyoRoomsURL);
		try {
			if (!alreadyAllowedPermission) {
				Thread.sleep(2000);
				// makeCall();
				alreadyAllowedPermission = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boolean alreadyAllowedPermission() {

		return false;
	}

	public void openHotelPage(String hotelId, String hotelType, String hotelName) {
		customURL = oyoRoomsURL + hotelId + "-" + hotelType + "-" + hotelName;
		driver.get(customURL);
	}

	public void openURL(String testHotel) {
		driver.get(testHotel);
	}

	public String parseJSONToString(String keyVal, String filePath) {
		JSONParser parser = new JSONParser();
		try {
			obj = parser.parse(new FileReader(filePath));
		} catch (IOException | ParseException e) {
			e.getMessage();
		}
		JSONObject jsonObj = (JSONObject) obj;
		json_KeyValuePair[i][0] = keyVal.toString();
		json_KeyValuePair[i][1] = (String) jsonObj.get(keyVal);
		i++;
		return (String) jsonObj.get(keyVal);
	}

	// MANAS, function to create hash map of JSON

	public Map<String, String> parseJSONToHashMap(String fileName) {
		Map<String, String> dataMap = new HashMap<String, String>();
		JSONParser parser = new JSONParser();
		try {
			obj = parser.parse(new FileReader(fileName));
		} catch (IOException | ParseException e) {
			e.getMessage();
		}
		JSONObject jsonObj = (JSONObject) obj;
		Set x = jsonObj.keySet();
		for (Object y : x) {
			dataMap.put(y.toString(), jsonObj.get(y).toString());
		}
		return dataMap;
	}

	// MANAS CHOPRA
	public void hoverOnElement(WebElement ele) {
		// discardLocationAlert();
		Actions action = new Actions(driver);
		action.build();
		action.moveToElement(ele).click().build().perform();

	}

	public void hoverOnMDDElement(WebElement ele) {
		// discardLocationAlert();
		Actions action = new Actions(driver);
		// action.build();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		action.moveToElement(ele).build().perform();
	}

	public void discardLocationAlert() {

		try {

			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build();
			action.perform();
			driver.switchTo().alert().accept();
			action.click().build().perform();

		} catch (Exception e) {
			System.out.println("OYOWeb: Unable to allow location" + e.getMessage());
		}
	}

	public void waitForElement(By locator, int timeOut, String element) {
		try {
			WebDriverWait wait = null;
			wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			for (int i = 0; i < json_KeyValuePair.length; i++) {
				if (json_KeyValuePair[i][1] == element) {
					String message = json_KeyValuePair[i][0] + " not found";
					printConsoleLogglyMessage(message);
					// System.out.println(message);
				}
			}
		}
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public String getCurrentWindow() {
		return driver.getWindowHandle();
	}

	public void switchToAnotherWindow(String currentWindow) {
		driver.switchTo().window(currentWindow);
	}

	public By locateById(String element) {
		return By.id(element);
	}

	public By locateByClassName(String element) {
		return By.className(element);
	}

	public By locateByCssSelector(String element) {
		return By.cssSelector(element);
	}

	public By locateByLinkText(String element) {
		return By.linkText(element);
	}

	public By locateByName(String element) {
		return By.name(element);
	}

	public By locateByPartialLinkText(String element) {
		return By.partialLinkText(element);
	}

	public By locateByXpath(String element) {
		return By.xpath(element);
	}

	public By locateByTagName(String element) {
		return By.tagName(element);
	}

	public WebElement findElementById(String element) {
		waitForElement(locateById(element), 20, element);
		return driver.findElement(By.id(element));
	}

	public List<WebElement> findElementsById(String element) {
		waitForElement(locateById(element), 20, element);
		return driver.findElements(By.id(element));
	}

	public WebElement findElementByClassName(String element) {
		waitForElement(locateByClassName(element), 20, element);
		return driver.findElement(By.className(element));
	}

	public WebElement findElementByTagName(String element) {
		waitForElement(locateByTagName(element), 20, element);
		return driver.findElement(By.tagName(element));
	}

	public List<WebElement> findElementsByTagName(String element) {
		return driver.findElements(By.tagName(element));
	}

	public WebElement findElementByXpath(String element) {
		waitForElement(locateByXpath(element), 120, element);
		return driver.findElement(By.xpath(element));
	}

	public List<WebElement> findElementsByXpath(String element) {
		waitForElement(locateByXpath(element), 120, element);
		return driver.findElements(By.xpath(element));
	}

	public WebElement findElementByCss(String element) {
		waitForElement(locateByCssSelector(element), 20, element);
		return driver.findElement(By.cssSelector(element));
	}

	public List<WebElement> findElementsByCss(String element) {
		return driver.findElements(By.cssSelector(element));
	}

	public WebElement findElementByLinkText(String element) {
		waitForElement(locateByLinkText(element), 20, element);
		return driver.findElement(By.linkText(element));
	}

	public List<WebElement> findElementsByLinkText(String element) {
		waitForElement(locateByLinkText(element), 10, element);
		return driver.findElements(By.linkText(element));
	}

	public WebElement findElementByPartialLinkText(String element) {
		waitForElement(locateByPartialLinkText(element), 20, element);
		return driver.findElement(By.partialLinkText(element));
	}

	public List<WebElement> findElementsByClassName(String element) {
		waitForElement(locateByClassName(element), 20, element);
		return driver.findElements(By.className(element));
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		int monthInInteger = cal.get(Calendar.MONTH);
		int currentMonth = monthInInteger + 1;
		return currentMonth;
	}

	public String getParentHandle() {
		String parentHandle = driver.getWindowHandle(); // get the current
														// window handle
		// System.out.println(parentHandle); //Prints the parent window handle
		return parentHandle;
	}

	public void switchToParentHandle(String parentHandle) {
		driver.switchTo().window(parentHandle);
	}

	public void navigateToNewTab() {
		for (String winHandle : driver.getWindowHandles()) { // Gets the new
																// window handle
			driver.switchTo().window(winHandle); // switch focus of WebDriver to
													// the next found window
													// handle (that's your newly
													// opened window)
			isNavigated = true;
		}

	}

	public void navigateToOther() {
		for (String winHandle : driver.getWindowHandles()) { // Gets the new
																// window handle
			driver.switchTo().window(winHandle); // switch focus of WebDriver to
													// the next found window
													// handle (that's your newly
													// opened window)
			// driver.quit();
		}
		isNavigated = false;

	}

	// public Boolean parseConsoleLog(String expectedMessage)
	// {
	// //parserTag
	//
	// try {
	// Thread.sleep(10000);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
	// System.out.println("\n---------------------------------\n" +
	// logEntries.toString() + "\n---------------------------------\n");
	//
	// for (LogEntry entry : logEntries) {
	// System.out.println( entry.getMessage());
	// //do something useful with the data
	// }
	// return false;
	// }

	public void takeScreenShot(String testClassName, String testMethodName) {

		// getting current date and time into string Ex: Thu Feb 18 18:13:38 IST
		// 2016
		Date currentDate = new Date();
		String currentDateAndTime = currentDate.toString();

		// splitting the date string Ex: "Thu Feb 18 18" out of "Thu Feb 18
		// 18:13:38 IST 2016"
		String[] dateFolder = currentDateAndTime.split(":");
		System.out.println(driver.getCurrentUrl());
		System.out.println();
		String newFolderName = filePathScreenShot + fileSeperator + dateFolder[0] + fileSeperator;

		File targetFolder = new File(newFolderName);
		if (!targetFolder.exists()) {
			// System.out.println("File created " + targetFolder);
			targetFolder.mkdir();
		}

		// taking screenshot
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// adding test class name before the test method while creating
		// screenshot file
		File targetFile = new File(newFolderName + testClassName + "_" + testMethodName + ".png");

		// The below method will save the screen shot with test class and method
		// name
		try {
			FileUtils.copyFile(scrFile, targetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCurrentPageUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	public String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		return reqTestClassname[i];
	}

	public void alertUsers(String alertMessage) throws Exception {
		//sendSMStoAll(alertMessage);
		// makeCall();
	}

	/*public void sendSMStoAll(String message) throws Exception {

		// Block to change normal text to %20 text
		try {
			
			String msg_mod = URLEncoder.encode(message, "UTF-8");
			
			// HashMap for Contact and TestCase Directory
			GoogleSheetReader sheet = new GoogleSheetReader();
			Map<String, String> cont_dir=sheet.getSheetMap("1yRf6EmdUOKdqyp5khYf2rpW9nqNRrnGvqZBkvmp-6f0", "AlertContact");
			String cont_str="";
			Set<String> cont_dir_key=cont_dir.keySet();
			int counter=0;
			for(String personName:cont_dir_key)
			{	counter++;
				cont_str+=cont_dir.get(personName);
				if(counter<cont_dir_key.size())
				{
					cont_str+=",";
				}
			}
			URL url=new URL("http://enterprise.smsgupshup.com/GatewayAPI/rest?method=sendMessage&msg="+msg_mod+"&msg_type=text&password=j2C74A&send_to=%2B"+cont_str+"&userid=2000143219");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.connect();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				buffer.append(line).append("\n");
			}
			System.out.println(buffer.toString());
			rd.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		// String smsAPIUrl =
		// "http://124.124.44.49:8080/alertMachine/alert/sender";
		// String[] numbers = alertNumbers.split(";");
		// String updatedUrl = "";
		// for (String number : numbers) {
		// updatedUrl = smsAPIUrl + "/" + number + "/" + message;
		// System.out.println("Sending" + updatedUrl);
		// getRequest(updatedUrl);
		// Thread.sleep(5000);
		// updatedUrl = "";
		// }

	}
*/
	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public void makeCall() throws Exception {
	//
	// String callAPIUrl = "";
	// if(execution_env.equals("remote"))
	// {
	// callAPIUrl = "http://124.124.44.49:8080/alertMachine/alert/sender";
	// }
	// else
	// {
	// callAPIUrl = "http://10.10.1.148:8080/alertMachine/alert/sender";
	// }
	// // callAPIUrl = callAPIUrl + "/9971999659";
	// postRequest(callAPIUrl);
	// }

	public void printConsoleLogglyMessage(String message) {
		System.out.println(message);
		try {
			postRequest("http://logs-01.loggly.com/inputs/3a390e0e-b2d2-4af0-a059-0c69a060bf7c/tag/OYO-QA",
					"'" + message + "'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	// HTTP GET request
	public String getRequest(String request) throws Exception {
		String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(request);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// // add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		//
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + request);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		inputLine = response.toString();
		//
		// // print result
		System.out.println(response.toString());
		return inputLine;

		//
	}

	//
	// HTTP POST request
	public void postRequest(String request) throws Exception {
		String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(request);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		// int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + request);
		// System.out.println("Post parameters : " + urlParameters);
		// System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		// System.out.println(response.toString());
	}

	// HTTP POST request with data
	public void postRequest(String request, String data) throws Exception {
		String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(request);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		String urlParameters = data;

		// add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Length", "" + Integer.toString(data.getBytes().length));
		con.setRequestProperty("Content-Type", "text/plain");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		// int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + request);
		// System.out.println("Post parameters : " + urlParameters);
		// System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		// System.out.println(response.toString());
	}

	public void dismissAlert() {

		if (isElementPresent(locateByTagName("iframe"))) {
			{
				if (isElementPresent(locateById("webklipper-publisher-widget-container-notification-frame"))) {
					driver.switchTo()
							.frame(findElementById("webklipper-publisher-widget-container-notification-frame"));
					findElementById("webklipper-publisher-widget-container-notification-close-div").click();
				}
			}

			driver.switchTo().defaultContent();
		}

	}

	public void scrollPage(String Xpath) {
		Point loc = driver.findElement(By.xpath(Xpath)).getLocation();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("javascript:window.scrollBy(0," + loc.y + ")");
		this.sleep(2000);
	}

	public void scrollPageById(String id) {
		Point loc = driver.findElement(By.id(id)).getLocation();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("javascript:window.scrollBy(0," + loc.y + ")");
		this.sleep(2000);
	}

	public void scrollPageByClassName(String className) {
		Point loc = driver.findElement(By.className(className)).getLocation();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("javascript:window.scrollBy(0," + loc.y + ")");
		this.sleep(2000);
	}

	// This function will select dropDown

	public void selectDropDownById(String id, int index) {
		Select dropDown = new Select(findElementById(id));
		dropDown.selectByIndex(index);

	}

	public List<WebElement> listOfWebElementByClassName(String className) {
		By web = By.className(className);
		List<WebElement> myElements = driver.findElements(web);
		return myElements;
	}
	public List<WebElement> listOfWebElementById(String id) {
		By web = By.className(id);
		List<WebElement> myElements = driver.findElements(web);
		return myElements;
	}

	public void clickOnIndexofClassName(int index, String className) {
		List<WebElement> li = listOfWebElementByClassName(className);
		li.get(index).click();

	}

	public String getTextOfUsingClassNameAndIndex(int index, String className) {
		List<WebElement> li = listOfWebElementByClassName(className);
		String text = li.get(index).getText();
		
		return text;
	}

	public boolean isElementVisible(String Locator) {
		boolean flag = driver.findElementByXPath(Locator).isDisplayed();
		return flag;
	}

	public String deviceId() throws Exception {

		String callAPIUrl = "";
		if (execution_env.equals("remote")) {
			callAPIUrl = "http://61.12.86.235:8080/alertMachine/alert/sender";
		} else {
			callAPIUrl = "http://10.10.64.130:8080/alertMachine/alert/sender";
		}
		callAPIUrl = callAPIUrl + "/12" + "/12";
		String device = getRequest(callAPIUrl);
		String[] off = device.split("	");
		String[] deviceId = off[0].split(">");

		return deviceId[2];
	}
	
	public void enterTextAppium(String Text)
	{
		
		driver.getKeyboard().sendKeys(Text);
		
	}
	
	public void scrollInsideView()
	{
		
		
		
	}

}
