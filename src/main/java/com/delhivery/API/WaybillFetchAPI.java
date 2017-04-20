package com.delhivery.API;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.relevantcodes.extentreports.LogStatus;

import utils.RestAPIUtility;

public class WaybillFetchAPI {

	RestAPIUtility apiobject=null;

	JSONArray wbns=null;
	String waybill[] = null;

	JSONObject jsonObject=null;		

	public WaybillFetchAPI() throws Exception{
		apiobject = new RestAPIUtility();
		wbns=new JSONArray();	
	}


	public String[] waybill_Fetch_API(String BaseUrl,Map<String,String> header, String client, int count){

		String waybill[] = new String[count];
		try {
			System.out.println("================Waybill Fetch API===========================");
			String WaybillFetchurl = BaseUrl
					+"/api/wbn/bulk.json?mode=Surface&consume=False&cl="
					+client
					+"&count="
					+count;
			
			System.out.println(WaybillFetchurl);
			
			jsonObject = apiobject.GetRequest(WaybillFetchurl, header);
			wbns = (JSONArray) jsonObject.get("wbns");
			//ExtentTestManager.getTest().log(LogStatus.PASS, "Waybill Array"+ wbns);
			for(int i=0;i<wbns.size();i++){
				waybill[i] =wbns.get(i).toString();	
				System.out.println(waybill[i]);
				//ExtentTestManager.getTest().log(LogStatus.PASS, "Waybill Created are: "+ waybill[i]);
			}
			
				
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return waybill;
	}

	
}
