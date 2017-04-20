package tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.testng.annotations.Test;

import com.delhivery.API.FmIncoming;
import com.delhivery.API.GenericIncomingAPI;
import com.delhivery.API.ManifestationAPI;
import com.delhivery.API.WaybillFetchAPI;

public class PackageCrationFlow {

	
	WaybillFetchAPI waybillObject;
	ManifestationAPI manifestObj;
	FmIncoming fmi;
	GenericIncomingAPI gn;
	
	Map<String,String> headerWaybill;
	Map<String,String> headerCMU;
	
	String auth=null;
	String BaseUrl = null;
	String client = null;
	int count;
	public static String[] waybill=null;
	String center="Mumbai MIDC (Maharashtra)";

	
	@org.testng.annotations.BeforeClass
	public void Setup() throws Exception{
		
		BaseUrl = "https://shreyvats-express.delhivery.com";
		client = "99tshirts";
		count=2;
		waybill=new String[2];
		
		waybillObject = new WaybillFetchAPI();
		
		headerWaybill = new HashMap<String, String>();
		headerCMU = new HashMap<String, String>();
		
		auth = "Token 0f9d5d8253ba145b6cfed882fc3bb610324d6ee8";
		
		headerWaybill.put("Accept", "application/json");
		headerWaybill.put("Authorization", auth);
		
		headerCMU.put("Accept", "application/x-www-form-urlencoded");
		headerCMU.put("Content-Type", "application/x-www-form-urlencoded");
		headerCMU.put("Authorization", auth);
	
	}
	
	
	@Test(priority = 0)
	public void testWayillFetch(){
		System.out.println(BaseUrl);
		System.out.println(client);
		System.out.println(count);
		waybill =waybillObject.waybill_Fetch_API(BaseUrl,headerWaybill,client, count);
	}

	
/*	@Test(priority = 1, dependsOnMethods = {"testWayillFetch"})
	public void testManifestAPI(){
		waybill = manifestObj.cmuApi_push(BaseUrl, headerCMU, waybill);
	}

	@Test(priority = 2, dependsOnMethods = {"testManifestAPI"})
	public void testFmIncomingAPI(){
		fmi.FMIncomingAPICall(BaseUrl, waybill, client, center, headerWaybill);
	}

	@Test(priority = 3, dependsOnMethods = {"testFmIncomingAPI"})
	public void testGenericIncomingAPI(){
		gn.generic_Incoming_API(BaseUrl, center, waybill,headerWaybill);
	}

*/}
