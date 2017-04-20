package com.delhivery.API;

import java.util.Map;

import org.json.simple.JSONObject;

import utils.RestAPIUtility;

public class GenericIncomingAPI {

	RestAPIUtility apiobject = new RestAPIUtility();
	
	public void generic_Incoming_API(String BaseUrl, String center, String[] waybill, Map<String, String> header){

		try {
			String genericIncomingUrl = BaseUrl+"/api/mob-loc/mi/";
			//test=ExtentTestManager.getTest();

			String requestContent = "{"
					+"\"center\":\""+center+"\","
					+"\"ref_ids\":[\""
					+waybill[0]
					+"\"]"
					+"}";
			System.out.println("================Generic Incoming=======================");

			System.out.println("\n Request Content: "+requestContent);	
			//test.log(LogStatus.PASS, "Request Content: "+requestContent);
			
			JSONObject jsonObject = apiobject.PostRequestWithBodyAsString(genericIncomingUrl, header, requestContent);
			
			String response = jsonObject.toString();
			
			System.out.println("Response : "+response);
			//test.log(LogStatus.PASS, "Response: "+response);
			
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}
