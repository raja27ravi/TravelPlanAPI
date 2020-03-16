package com.intuit.travelapi.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import com.intuit.travelapi.entity.CurrencyConvReqParams;
import com.intuit.travelapi.entity.CurrencyConvRespParams;
import com.intuit.travelapi.entity.DestinationInfoReqParams;
import com.intuit.travelapi.entity.DestinationInfoRespParams;
import com.intuit.travelapi.entity.ErrorCode;
import com.intuit.travelapi.entity.Severity;
import com.intuit.travelapi.exceptions.RestTemplateExceptionHandler;
import com.intuit.travelapi.service.TravelPlanService;

@RestController
@RequestMapping("/api")
public class TravelPlanRestController {

	
	private TravelPlanService travelPlanService;
	
	@Autowired
	private RestTemplateExceptionHandler restexcephandler;
	public TravelPlanRestController(TravelPlanService thetravelPlanService) {
		
		travelPlanService = thetravelPlanService;
	}
	
	@PostMapping(path = "/destinfo", consumes = "application/json", produces = "application/json")
	public DestinationInfoRespParams getDestinationInfo(@Valid @RequestBody DestinationInfoReqParams destinationInfoReqParams) throws Exception  {
		
		//Payload validation
		//if(destinationInfoReqParams.getCity()!=null) {
		DestinationInfoRespParams destinationInfoErrorMessage = new DestinationInfoRespParams();  
		
		//Date Validation
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate from = LocalDate.parse(destinationInfoReqParams.getTravel_date());
		LocalDateTime now = LocalDateTime.now();
		
		///compare from and now date and see if it lies with in 5 days or not
		long days_bwtn = ChronoUnit.DAYS.between(from, now);
		
		if((days_bwtn < -5) || (days_bwtn > 0)) {
			
			restexcephandler.throwInternalException(ErrorCode.DATA_ERROR,"Validation Error","Date is more  than 5 days","Please enter a date from today till next 5 days",Severity.LOW,HttpStatus.BAD_REQUEST);
			
		return null;
		}
		
		else {
		DestinationInfoRespParams destinationInfoRespParams = null;
	
			destinationInfoRespParams = travelPlanService.getDestinationInfo(destinationInfoReqParams);
		
		return destinationInfoRespParams;
		}
		
	}
	////Post mapping for currency conversion API

	@PostMapping(path = "/currconv", consumes = "application/json", produces = "application/json")
	public CurrencyConvRespParams getCurrencyConversionInfo(@Valid @RequestBody CurrencyConvReqParams currencyConvReqParams) {
		
		CurrencyConvRespParams currencyConvRespForError = new CurrencyConvRespParams();
		//payload validation
		
		 if((currencyConvReqParams.getAmount()==0.0) || (Double.compare(currencyConvReqParams.getAmount(),0.0) < 0))
				{
			
			double flag = Double.compare(currencyConvReqParams.getAmount(), 0.0);
			restexcephandler.throwInternalException(ErrorCode.DATA_ERROR,"Validation Error","Amount entered is either Negative or Zero","Please enter a valid amount",Severity.LOW,HttpStatus.BAD_REQUEST);;
			return null;
		}
	
		else {
		CurrencyConvRespParams currencyConvRespParams = travelPlanService.getCurrencyConversionInfo(currencyConvReqParams);
			
		return currencyConvRespParams;
		}
	
	}
}
	
	

