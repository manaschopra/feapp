package utils;

import java.io.BufferedReader;
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


import io.appium.java_client.remote.MobileCapabilityType;

public class AppLauncher {

	public static AndroidDriver driver = null;

	String filePathConfig, browserType, device_Id, device_Type;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	protected Helper helper = new Helper();
	
    String localApp = "LastMile-1.0.0.13.apk";  // production release

    @BeforeMethod
    public AndroidDriver setUp() throws Exception {

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"LenovoTAB2A8");
        //capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"device");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1");
        
        capabilities.setCapability(MobileCapabilityType.APP,"delhivery.lastmile.dev");
      //  capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,"activity.SplashActivity");
        
        
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
        capabilities.setCapability("appium-version", "1.6.3");
        String userDir = System.getProperty("user.dir");
        capabilities.setCapability("--session-override",true);
        String appPath = Paths.get(userDir, localApp).toAbsolutePath().toString();
        capabilities.setCapability(MobileCapabilityType.APP, appPath);
        
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
		String ipAddress = "mactech123:21f869db-6ec8-489a-ac48-c8aac1b4f11c@ondemand.saucelabs.com:80";
		String port1 = ":4723";
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
			driver = new AndroidDriver(new URL(nodeUrl), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
	
	@AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
	
}
