package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LogInPage;
import utils.AppLauncher;
import utils.Helper;

public class LogInPageTest extends AppLauncher {

	 LogInPage logInPage = new LogInPage();
    Helper helper= new Helper();
    AppLauncher a= new AppLauncher();
    
    
    
@Test(description = "Verify login1",priority=1)
	public void verifyLogInSuccessful() {
	String Home_text= logInPage.logIn();
	Assert.assertEquals(Home_text, "Last Mile");
	
	System.out.println(logInPage.shipment());
	
	}





	
	

@BeforeMethod()
public void login()
	{
        try {
			a.setUp();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
       
	}
	
	
@AfterMethod
public void after()
{
	try {
		a.tearDown();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	}
