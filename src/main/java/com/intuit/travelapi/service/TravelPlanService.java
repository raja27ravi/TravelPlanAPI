package com.intuit.travelapi.service;

import com.intuit.travelapi.entity.CurrencyConvReqParams;
import com.intuit.travelapi.entity.CurrencyConvRespParams;
import com.intuit.travelapi.entity.DestinationInfoReqParams;
import com.intuit.travelapi.entity.DestinationInfoRespParams;

public interface TravelPlanService {
	
	public DestinationInfoRespParams getDestinationInfo(DestinationInfoReqParams destinationInfoReqParams) throws Exception;
	
	public CurrencyConvRespParams getCurrencyConversionInfo(CurrencyConvReqParams currencyConvReqParams);
	
	

}
