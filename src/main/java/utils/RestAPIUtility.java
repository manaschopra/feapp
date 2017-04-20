package utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RestAPIUtility {

	public JSONObject GetRequest(String URL,Map<String,String > headers) throws ParseException, ClientProtocolException, IOException{
		JSONObject jsonObject=null;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(URL);
		for(String key:headers.keySet()){
			getRequest.setHeader(key,headers.get(key));
		}
		HttpResponse response = httpClient.execute(getRequest);
		String output=IOUtils.toString(response.getEntity().getContent());
		//System.out.println(output);							
		httpClient.getConnectionManager().shutdown();

		//Response JSON parsing 
		JSONParser parser = new JSONParser();  				
		Object objParsed = parser.parse(output);
		jsonObject = (JSONObject) objParsed;

		return jsonObject;
	}
	public JSONObject PostRequestWithBody(String URL,Map<String,String > headers,JSONObject requestBody) throws ParseException, ClientProtocolException, IOException{
		JSONObject jsonObject=null;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(URL);
		for(String key:headers.keySet()){
			postRequest.setHeader(key,headers.get(key));
		}
		StringEntity input = new StringEntity(requestBody.toJSONString());
		postRequest.setEntity(input);
		HttpResponse response = httpClient.execute(postRequest);
		String output=IOUtils.toString(response.getEntity().getContent());
		System.out.println(output);							
		//Response JSON parsing 
		JSONParser parser = new JSONParser();  				
		Object objParsed = parser.parse(output);
		jsonObject = (JSONObject) objParsed;

		return jsonObject;
	}	

	public JSONObject PostRequestWithBodyAsString(String URL,Map<String,String > headers,String requestBody) throws ParseException, ClientProtocolException, IOException{
		JSONObject jsonObject=null;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(URL);
		for(String key:headers.keySet()){
			postRequest.setHeader(key,headers.get(key));
		}
		StringEntity input = new StringEntity(requestBody);
		input.setContentType("application/json");
		postRequest.setEntity(input);
		HttpResponse response = httpClient.execute(postRequest);
		String output=IOUtils.toString(response.getEntity().getContent());
		System.out.println(output);							
		//Response JSON parsing 
		JSONParser parser = new JSONParser();  				
		Object objParsed = parser.parse(output);
		jsonObject = (JSONObject) objParsed;

		return jsonObject;
	}		
	
	public String PostRequestWithBodyAndRetrunAsString(String URL,Map<String,String > headers,String requestBody) throws ParseException, ClientProtocolException, IOException{
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(URL);
		for(String key:headers.keySet()){
			postRequest.setHeader(key,headers.get(key));
		}
		StringEntity input = new StringEntity(requestBody);
		input.setContentType("application/json");
		postRequest.setEntity(input);
		HttpResponse response = httpClient.execute(postRequest);
		String output=IOUtils.toString(response.getEntity().getContent());
		System.out.println(output);							
		

		return output;
	}	
	

	
}
