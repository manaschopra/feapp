package com.delhivery.API;

import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utils.RestAPIUtility;

public class FmIncoming {
	
	RestAPIUtility fmPostCallObject = new RestAPIUtility();

	public void FMIncomingAPICall(String fmIncomingUrl, String[] waybill, String client, String center,Map<String, String> headers) {
		try{
			System.out.println("===============FMInomcing API==================");
			
			JSONArray jsonArray=null;
			
			String payload = fmInocmingPayload(center, waybill, client);

			//test.log(LogStatus.PASS, "FM Incoming Payload"+ payload);
			
			String output = fmPostCallObject.PostRequestWithBodyAndRetrunAsString(fmIncomingUrl, headers, payload);

			//test.log(LogStatus.PASS, "FM Incoming response output"+ output);
			
			//Response JSON parsing ---- Use this if JSONArray read is required---
			JSONParser parser = new JSONParser();  				
			Object objParsed = parser.parse(output);
			jsonArray = (JSONArray) objParsed;
			

		} catch (ParseException | IOException e) {

			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String fmInocmingPayload(String center,String[] waybill, String client){

		String payloadWbn = waybill[0];

/*		for(int i=1;i<waybill.length;i++){
			payloadWbn = payloadWbn+","+waybill[i];
			System.out.println(payloadWbn);
		}
*/
		String requestPayload = "{"
				+"\"center\":\""+center+"\","
				+"\"client\":\""+client+"\","
				+"\"waybill\":[\""+payloadWbn+"\"]"
				+"}";

		return requestPayload;

	}


}
