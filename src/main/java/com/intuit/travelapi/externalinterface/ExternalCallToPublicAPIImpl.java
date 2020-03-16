package com.intuit.travelapi.externalinterface;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
/*
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;*/
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/*import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;*/
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.intuit.travelapi.entity.WeatherDetails;
import com.intuit.travelapi.entity.CurrencyConvCodeDetails;
//import com.intuit.travelapi.entity.CurrencyCode;
//import com.intuit.travelapi.entity.CountryCodeReq;
import com.intuit.travelapi.entity.CurrencyConvReq;
import com.intuit.travelapi.entity.CurrencyConvReqParams;
//import com.intuit.travelapi.entity.CurrencyConvResp;
import com.intuit.travelapi.entity.CurrencyConvRespParams;
import com.intuit.travelapi.entity.DestinationInfoReqParams;
import com.intuit.travelapi.entity.DestinationInfoRespParams;
import com.intuit.travelapi.entity.ErrorCode;
import com.intuit.travelapi.entity.Severity;
/*import com.intuit.travelapi.entity.WeatherInfoReq;
import com.intuit.travelapi.entity.WeatherInfoResp;*/
import com.intuit.travelapi.exceptions.RestTemplateExceptionHandler;

@Component
public class ExternalCallToPublicAPIImpl implements ExternalCallToPublicAPI {

	//private static final String wikiAPIUrl = "http://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&titles=Cancun&exlimit=1&explaintext=1&exsentences=10&redirects=true";

	//// wiki 1st para fetching
	@Override
	public String getWikiFirstPara(DestinationInfoReqParams destinationInfoReqParams) throws Exception{
		// TODO Auto-generated method stub

		//DestinationInfoRespParams destinationInfoRespParams = new DestinationInfoRespParams();
		String destDescp= null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// headers.set("APIKey","");
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String resourceURL = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&titles=" +destinationInfoReqParams.getCity()+ "&exsentences=6&exlimit=1&redirects=true&explaintext=1/";
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(resourceURL, HttpMethod.GET, entity, String.class);
		}
		catch(Exception ex){
			RestTemplateExceptionHandler restexcephad = new RestTemplateExceptionHandler();
			restexcephad.handleRestRepositoryExceptions(ex, "Wiki", ErrorCode.CONNECTIVITY_ERROR, "Wiki API Error", "", Severity.MEDIUM, Severity.HIGH);
		}
		
		JSONObject root = new JSONObject(response.getBody().toString()); //
		JSONObject jsoncoord = new JSONObject(root.get("query").toString()); 
		jsoncoord.get("pages");
		
		//ObjectMapper mapper = new ObjectMapper();
				ObjectMapper mapper = new ObjectMapper();
			    JsonNode root_node = mapper.readTree(jsoncoord.get("pages").toString());
			    Iterator<String> fieldNames = root_node.fieldNames();
			    String fieldNameForPageId = null;
			    while (fieldNames.hasNext()) {
			  
			        String fieldName = fieldNames.next();
			        fieldNameForPageId= fieldName;
			   //we are traversing node here as it is tricky here in this API as page number is coming as key
			    //    JsonNode node = root_node.get(fieldName);

			        
			    	JSONObject jsonpage = new JSONObject(jsoncoord.get("pages").toString());
			
					JSONObject jsonpageextract = new JSONObject(jsonpage.get(fieldNameForPageId).toString());
			
					destDescp = jsonpageextract.get("extract").toString();
					
			    }
		
		return destDescp;
	}

	/// open waether api calls

	@Override
	public WeatherDetails getWeatherInfo(DestinationInfoReqParams destinationInfoReqParams) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// headers.set("APIKey","");
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String resourceURL = "http://api.openweathermap.org/data/2.5/weather?q=" + destinationInfoReqParams.getCity()
				+ "&APPID=ca19b96470391f5a34374cd63a9c7e86";
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = null;
		//restTemplate.exchange(resourceURL, HttpMethod.GET, entity, String.class);

		try {
			
			response = restTemplate.exchange(resourceURL, HttpMethod.GET, entity, String.class);
			
		}
		
		catch(Exception ex){
			RestTemplateExceptionHandler restexcephad = new RestTemplateExceptionHandler();
			restexcephad.handleRestRepositoryExceptions(ex, "Weather", ErrorCode.CONNECTIVITY_ERROR, " Weather API Error", "", Severity.MEDIUM, Severity.HIGH);
			System.out.println("catch " + response.getBody());
		}
		Gson gson = new Gson(); // String json = gson.toJson(weatherDetails);
		WeatherDetails weatherDetails = gson.fromJson(response.getBody(), WeatherDetails.class);

		
		
		return weatherDetails;
	}

///rest county code api for fetching the currency call
	@Override
	public String getCurrencyCodeForCountry(String countryCodeReq) {
		// TODO Auto-generated method stub
		//String CurrencyConvCode = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// headers.set("APIKey","");
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String resourceURL = "https://restcountries.eu/rest/v2/alpha/" + countryCodeReq;
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<CurrencyConvCodeDetails> currencyConvCodeDetails =null;
		try {
			//System.out.println("try blcok" + response.getBody());
			currencyConvCodeDetails = restTemplate.exchange(resourceURL, HttpMethod.GET, entity, CurrencyConvCodeDetails.class);
			
		}
		catch(Exception ex){
			RestTemplateExceptionHandler restexcephad = new RestTemplateExceptionHandler();
			restexcephad.handleRestRepositoryExceptions(ex, "Currency Conv Error", ErrorCode.CONNECTIVITY_ERROR, "Currency Conv API Error", "", Severity.MEDIUM, Severity.HIGH);
			//System.out.println("catch " + response.getBody());
		}
		
		
		/*
		 * Gson gson = new Gson(); CurrencyConvCodeDetails currencyConvCodeDetails =
		 * gson.fromJson(response.getBody(),CurrencyConvCodeDetails.class);
		 */	 
	  
		return Arrays.asList(currencyConvCodeDetails.getBody().getCurrencies()).stream().findFirst().get().getCode();
		/*
		 * for(CurrencyCode element : currencyConvCodeDetails.getBody().getCurrencies())
		 * { //System.out.println(element.getCode()); CurrencyConvCode=
		 * element.getCode(); }
		 */
		  
	
		
		//return CurrencyConvCode;
	}

	/// country conver api call

	@Override
	public String getConversionfactor(CurrencyConvReq currencyConvReq) {
		// TODO Auto-generated method stub
		
		String convFact;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// headers.set("APIKey","");
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String resourceURL = "https://free.currconv.com/api/v7/convert?q="
				+ currencyConvReq.getHomeCurrencyCode()+"_"+ currencyConvReq.getDestCurrencyCode() + "&compact=ultra&apiKey=e4b3db32307a9d1765ab";
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		//ResponseEntity<String> response = restTemplate.exchange(resourceURL, HttpMethod.GET, entity, String.class);
		 ResponseEntity<String> response =null;
			
		try {
			//System.out.println("try blcok" + response.getBody());
			response = restTemplate.exchange(resourceURL, HttpMethod.GET, entity, String.class);
			
		}
		 catch(Exception ex){
				RestTemplateExceptionHandler restexcephad = new RestTemplateExceptionHandler();
				restexcephad.handleRestRepositoryExceptions(ex, "Currency Conv Error", ErrorCode.CONNECTIVITY_ERROR, "Currency Conv API Error", "", Severity.MEDIUM, Severity.HIGH);
				System.out.println("catch " + response.getBody());
			}
		 JSONObject root = new JSONObject(response.getBody().toString()); //JSONObject
		 convFact = root.get(currencyConvReq.getHomeCurrencyCode()+"_"+ currencyConvReq.getDestCurrencyCode()).toString();
		 
	
		 
		
		
		return convFact;
	}

	@Override
	public DestinationInfoRespParams getDestinationInfo(DestinationInfoReqParams destinationInfoReqParams) throws Exception {
		// TODO Auto-generated method stub


		WeatherDetails weatherDetails = getWeatherInfo(destinationInfoReqParams);
		CurrencyConvReq currencyConvReq = new CurrencyConvReq();
		String destInfo= null;

		String country_Code = weatherDetails.getSys().getCountry();
		String dest_currency_code = getCurrencyCodeForCountry(country_Code);
		currencyConvReq.setDestCurrencyCode(dest_currency_code);
		currencyConvReq.setHomeCurrencyCode(destinationInfoReqParams.getHome_currency());
		String exchange_rate = getConversionfactor(currencyConvReq);
		//System.out.println(" dest_currency_code : " + dest_currency_code);

		DestinationInfoRespParams destinationInfoRespParams = new DestinationInfoRespParams();
		destInfo = getWikiFirstPara(destinationInfoReqParams);
		// call wiki para first api call and set methods here
		
		destinationInfoRespParams.setDescription(destInfo);
		destinationInfoRespParams.setWeatherDetails(weatherDetails);
		destinationInfoRespParams.setCity(weatherDetails.getName());
		// exchange rate fetching by country code fetching currency code fetch call to
		// rest api

		destinationInfoRespParams.setExchange_rate(exchange_rate);

		return destinationInfoRespParams;
	}
	////////edited code
	public WeatherDetails getWeatherInfoForConv(CurrencyConvReqParams currencyConvReqParams) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// headers.set("APIKey","");
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String resourceURL = "http://api.openweathermap.org/data/2.5/weather?q=" + currencyConvReqParams.getCity()
				+ "&APPID=ca19b96470391f5a34374cd63a9c7e86";
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(resourceURL, HttpMethod.GET, entity, String.class);

		Gson gson = new Gson(); // String json = gson.toJson(weatherDetails);
		WeatherDetails weatherDetails = gson.fromJson(response.getBody(), WeatherDetails.class);
		// weatherDetails.getWeather().

		if (response.getStatusCode() == HttpStatus.OK) {
		//	System.out.println("feels_like" + response.getBody());
		} else {
			//System.out.println("error : " + response.getBody());
		}
		return weatherDetails;
	}
	//////

	@Override
	public CurrencyConvRespParams getCurrencyConversionInfo(CurrencyConvReqParams currencyConvReqParams) {
		// TODO Auto-generated method stub
		
		///CALL 
		WeatherDetails weatherDetails = getWeatherInfoForConv(currencyConvReqParams);
		CurrencyConvReq currencyConvReq = new CurrencyConvReq();
	

		String country_Code = weatherDetails.getSys().getCountry();
		String dest_currency_code = getCurrencyCodeForCountry(country_Code);
		currencyConvReq.setDestCurrencyCode(dest_currency_code);
		currencyConvReq.setHomeCurrencyCode(currencyConvReqParams.getHome_currency());
		String exchange_rate = getConversionfactor(currencyConvReq);
		CurrencyConvRespParams currencyConvRespParams = new CurrencyConvRespParams();
		currencyConvRespParams.setCity(currencyConvReqParams.getCity());
		currencyConvRespParams.setDestination_currency(dest_currency_code);
		currencyConvRespParams.setHome_currency(currencyConvReqParams.getHome_currency());
		currencyConvRespParams.setHome_amount(currencyConvReqParams.getAmount());
	
		double exchange_rate_mul=Double.parseDouble(exchange_rate);
		
		//Multiplying the home currency with conversion factor
		double dest_amt = exchange_rate_mul * currencyConvReqParams.getAmount();
		
		currencyConvRespParams.setDestination_amount(dest_amt);
		
		return currencyConvRespParams;
	}

}
