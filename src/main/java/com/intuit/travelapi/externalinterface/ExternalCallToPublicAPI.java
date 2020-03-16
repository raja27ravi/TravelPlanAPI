package com.intuit.travelapi.externalinterface;

//import com.intuit.travelapi.entity.CountryCodeReq;
import com.intuit.travelapi.entity.CurrencyConvReq;
import com.intuit.travelapi.entity.CurrencyConvReqParams;
import com.intuit.travelapi.entity.CurrencyConvResp;
import com.intuit.travelapi.entity.CurrencyConvRespParams;
import com.intuit.travelapi.entity.DestinationInfoReqParams;
import com.intuit.travelapi.entity.DestinationInfoRespParams;
import com.intuit.travelapi.entity.WeatherDetails;
import com.intuit.travelapi.entity.WeatherInfoReq;
import com.intuit.travelapi.entity.WeatherInfoResp;

public interface ExternalCallToPublicAPI {

public DestinationInfoRespParams getDestinationInfo(DestinationInfoReqParams destinationInfoReqParams) throws Exception;
	
public String getWikiFirstPara(DestinationInfoReqParams destinationInfoReqParams) throws Exception;	

public WeatherDetails getWeatherInfo(DestinationInfoReqParams destinationInfoReqParams);


public String getCurrencyCodeForCountry(String countryCodeReq); 

public String getConversionfactor(CurrencyConvReq currencyConvReq);

public CurrencyConvRespParams getCurrencyConversionInfo(CurrencyConvReqParams currencyConvReqParams);
	
}
