package pages;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;

public class LogInPage extends BasePage {

	
	public void isValid() {

	}

	
	public void logIn() {
		
		String loginId = "delhivery.lastmile.dev:id/login_phone_et";
		helper.findElementById(loginId).sendKeys("9654988087");

		helper.sleep(1000);
		
		
		String getOtpBtn = "delhivery.lastmile.dev:id/login_get_otp";
		helper.findElementById(getOtpBtn).click();
		helper.sleep(10000);
		
		String Text= helper.findElementByXpath("//android.widget.TextView[@text='Last Mile']").getText();
		System.out.println(Text);
		
	}	
		
	}

	