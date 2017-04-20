package pages;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;

import utils.Helper;

public class LogInPage extends BasePage {

	
	String HomePage = "./src/main/resources/objectRepo/HomePage.json";
	Helper help= new Helper();
	public void isValid() {

	}

	
	public String  logIn() {
		
		String loginId = "delhivery.lastmile.dev:id/login_phone_et";
		helper.findElementById(loginId).sendKeys("9654988087");

		helper.sleep(1000);
		
		
		String getOtpBtn = "delhivery.lastmile.dev:id/login_get_otp";
		helper.findElementById(getOtpBtn).click();
		helper.sleep(10000);
		
		String Text= helper.findElementByXpath("//android.widget.TextView[@text='Last Mile']").getText();
		System.out.println(Text);
		return Text;
		
	}	
		public String shipment()
		{
			String shipment_xpath=helper.parseJSONToString("shipment_text", HomePage);
			System.out.println(shipment_xpath);
			//String shipment_Text=helper.findElementByXpath(shipment_xpath).getText();
			helper.findElementById("delhivery.lastmile.dev:id/item_todo_seller").click();
			helper.sleep(1000);
			return shipment_xpath;
		}
	}

	