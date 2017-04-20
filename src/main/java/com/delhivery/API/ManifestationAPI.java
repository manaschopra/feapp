package com.delhivery.API;

import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utils.RestAPIUtility;

public class ManifestationAPI {
	
	
	RestAPIUtility apiobject = new RestAPIUtility();
	public JsonObject jsonObject;
	public static String createdWaybill[] = new String[2];

	public String[] cmuApi_push(String BaseUrl,Map<String, String> headers,String[] waybill){
		try {
			String CMUPushUrl = BaseUrl+"/cmu/push/json/";
			System.out.println("==============Manifestation API===================");
			for(int i=0;i<waybill.length;i++){
				//ExtentTestManager.getTest().log(LogStatus.PASS, "Waybill is "+ waybill[i]);
				String requestContent = CmuApiPayload(waybill[i]);
				
				//ExtentTestManager.getTest().log(LogStatus.PASS, "Request Paylload is: "+ requestContent);
				
				JSONObject jsonObject = apiobject.PostRequestWithBodyAsString(CMUPushUrl, headers, requestContent);

				String response = jsonObject.toString();

				createdWaybill[i] = (String) method(response,"waybill");
				
				//ExtentTestManager.getTest().log(LogStatus.PASS, "Response generated is: "+ response);
				//ExtentTestManager.getTest().log(LogStatus.PASS, "Waybill Successfully Created is: "+ createdWaybill[i]);
								
			}

		
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return createdWaybill;
	}
	
	
	public String CmuApiPayload(String waybill){

		int Orderno = (int )(Math. random() * 10000 + 1);		

		String payload = "data={"+
				"\"shipments\": [{"+
				"\"name\": \"Avni-TestOrder\","+
				"\"client\": \"99tshirts\","+
				"\"order\": \"FeAPP-Avni"+Orderno+"\","+
				"\"waybill\":\""+waybill+"\","+
				"\"products_desc\": \"99labels Goods\","+
				"\"order_date\": \"2014-05-08T12:30:00 00:00\","+
				"\"payment_mode\": \"Prepaid\","+
				"\"cod_amount\": 1001,"+
				"\"total_amount\": 1001,"+
				"\"add\": \"474/4, New Railway Road\","+
				"\"city\": \"Delhi\","+
				"\"state\": \"Delhi\","+
				"\"country\": \"India\","+
				"\"taxation_type\":\"B2B\","+
				"\"phone\": \"99999999999\","+
				"\"pin\": \"400059\","+
				"\"return_add\": \"\","+
				"\"return_city\": \"\","+
				"\"return_country\": \"\","+
				"\"return_name\": \"\","+
				"\"return_phone\": \"\","+
				"\"return_pin\": \"122001\","+
				"\"return_state\": \"\","+
				"\"supplier\": \"Kangaroo (India) Pvt Ltd\","+
				"\"billable_weight\": 650,"+
				"\"dimensions\": \"9.00CM x 9.00CM x 9.00CM\","+
				"\"volumetric\": 0,"+
				"\"weight\": \"650.0 gm\""+
				"}],"+
				"\"dispatch_date\": \"2016-08-01T18:00:00.000000 05:30\","+
				"\"pickup_location\": {"+
				"\"shp_name\":\"bkjkjned\","+
				"\"name\": \"99tshirts\","+
				"\"products_desc\": \"99labels Goods\","+
				"\"add\": \"Street X, ABC\","+
				"\"city\": \"Delhi\","+
				"\"state\": \"Delhi\","+
				"\"total_amount\": 1001,"+
				"\"pin\": \"110030\","+
				"\"country\": \"India\","+
				"\"phone\": \"9999999999\","+
				"\"cod_amount\": 1001,"+
				"\"return_add\": \"\","+
				"\"return_city\": \"\","+
				"\"return_country\": \"\","+
				"\"return_name\": \"\","+
				"\"return_phone\": \"\","+
				"\"return_pin\": \"110040\","+
				"\"return_state\": \"\","+
				"\"supplier\": \"Kangaroo (India) Pvt Ltd\","+
				"\"billable_weight\": 650,"+
				"\"dimensions\": \"9.00CM x 9.00CM x 9.00CM\","+
				"\"volumetric\": 0,"+
				"\"weight\": \"650.0 gm\""+
				"}"+
				"}&format=json";
		return payload;
	}

	static String method(String json, String findEle)
	{
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(json).getAsJsonObject();
		try {
			String str = obj.get(findEle).getAsString();
			return str;
		}catch (NullPointerException e)
		{
			System.out.print("...");
		}
		JsonArray array = obj.get("packages").getAsJsonArray();
		Iterator it = array.iterator();
		while(it.hasNext()){
			try {
				return method(it.next().toString(), findEle);
			}catch (Exception e){

			}
		}
		return null;
	}



}
