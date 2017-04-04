package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import io.appium.java_client.remote.MobileCapabilityType;

public class AppLauncher {

	public static AndroidDriver driver = null;
	static AppiumDriverLocalService service;
	   static String service_url ;
	  
	String filePathConfig, browserType, device_Id, device_Type;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	protected Helper helper = new Helper();
	
    String localApp = "LastMile-1.0.0.13.apk";  // production release

    
    public AndroidDriver setUp() throws Exception {
    	
    	 service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(0).usingDriverExecutable(new File("/home/delhivery/.linuxbrew/bin/node")).withAppiumJS(new File("/home/delhivery/.linuxbrew/bin/appium")));
         service_url = service.getUrl().toString();
         service.start();
    	
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        
       
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"device");
       
        capabilities.setCapability(MobileCapabilityType.APP,"delhivery.lastmile.dev");
     
        
        
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
        capabilities.setCapability("appium-version", "1.6.3");
        String userDir = System.getProperty("user.dir");
        capabilities.setCapability("--session-override",true);
        String appPath = Paths.get(userDir, localApp).toAbsolutePath().toString();
        capabilities.setCapability(MobileCapabilityType.APP, appPath);
        System.out.println(appPath);
        driver = initDriver();
    
        
        return driver;
    }

  public AndroidDriver initDriver() {
		filePathConfig = "./src/main/resources/config/config.json";
		String execution_env = helper.parseJSONToString("execution_environment", filePathConfig);
		String nodeUrl1 = "http://";
		String nodeip = "";
		String nodeUrl2 = "/wd/hub";
		String nodeUrl = "";
		
		String publicipAddress="0.0.0.0";;
		
		//check and set the execution environment
		switch (execution_env) {
		case "local":
			publicipAddress = "0.0.0.0";
			break;
		
		default:
			publicipAddress = "0.0.0.0";
			break;
		}

		
		if (execution_env.equals("local")) {			
			nodeip = publicipAddress;
			nodeUrl = nodeUrl1 + nodeip + port1 + nodeUrl2;
			
		}
		
		
		//creating the Android driver instance
		try {
			driver = new AndroidDriver(new URL(service_url), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
	
	
    public void tearDown() throws Exception {
        driver.quit();
    }
	
}
