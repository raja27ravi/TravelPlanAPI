package com.intuit.travelapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.travelapi.entity.CurrencyConvReqParams;
import com.intuit.travelapi.entity.CurrencyConvRespParams;
import com.intuit.travelapi.entity.DestinationInfoReqParams;
import com.intuit.travelapi.entity.DestinationInfoRespParams;
import com.intuit.travelapi.externalinterface.ExternalCallToPublicAPI;

@Service
public class TravelPlanServiceImpl implements TravelPlanService {
	
	
	private ExternalCallToPublicAPI externalCallToPublicAPI;
	
	@Autowired
	public TravelPlanServiceImpl(ExternalCallToPublicAPI theexternalCallToPublicAPI) {
		
		externalCallToPublicAPI = theexternalCallToPublicAPI;
	}

	@Override
	public DestinationInfoRespParams getDestinationInfo(DestinationInfoReqParams destinationInfoReqParams) throws Exception {
		// TODO Auto-generated method stub
		
		// create a destinationinforespparams object and do all client calls and set all the requested params
		
		///creating a client class in external interface  package and call wiki api client inside a method. use that
		//method here in calling and set the destination info resp params and send it back again to controller
		
		
		//here we need to gather info from all the different apis individually and combine and set every parameter in destioninforesprams
		///and send it agaian from there after successful combining
		
		DestinationInfoRespParams destinationInfoRespParams = externalCallToPublicAPI.getDestinationInfo(destinationInfoReqParams);
		
		
		
		//getWikiFirstPara method---------------->>>1111111>>>>>>>>>>>>>>>>>>
		
		//create another client in the external interface package and call weather client  inside a method. use that
		//method here in calling and set the destination info resp params and send it back again to controller
		
		
		//create another client in the external interface package and call currency conversion  client  inside a method. use that
		//method here in calling and set the destination info resp params and send it back again to controller
		
		
		return destinationInfoRespParams;
	}

	@Override
	public CurrencyConvRespParams getCurrencyConversionInfo(CurrencyConvReqParams currencyConvReqParams) {
		// TODO Auto-generated method stub
		
		
		///get the country code from weather api.. use that in https://restcountries.eu/rest/v2/alpha/? in ? and get the currency code 
		
		// and get the currency conversion from https://free.currconv.com/api/v7/convert?q=CAD_MXN&compact=ultra&apiKey=e4b3db32307a9d1765ab 
		CurrencyConvRespParams currencyConvRespParams = externalCallToPublicAPI.getCurrencyConversionInfo(currencyConvReqParams);
		
		return currencyConvRespParams;
	}

}
