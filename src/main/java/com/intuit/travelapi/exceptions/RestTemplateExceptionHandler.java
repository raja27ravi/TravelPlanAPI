package com.intuit.travelapi.exceptions;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.*;

import com.intuit.travelapi.entity.ErrorCode;
import com.intuit.travelapi.entity.Severity;

@Component
public class RestTemplateExceptionHandler {

	
	
	   public void handleRestRepositoryExceptions(Exception ex, String repositoryApplicationName, ErrorCode errorCode, String errorType, String rootCause, Severity clientExceptionSeverity, Severity serverExceptionSeverity) {
	        if(ex instanceof ResourceAccessException) {
	            if(Objects.nonNull(ex.getCause()))
	                errorType = ex.getCause().getClass().getSimpleName().concat("-").concat(errorType);
	            else
	                errorType = ex.getClass().getSimpleName().concat("-").concat(errorType);
	            throwInternalException(errorCode, errorType, ex.getMessage(), rootCause, Severity.HIGH, HttpStatus.SERVICE_UNAVAILABLE);
	        }
	   
	        else if (ex instanceof HttpClientErrorException) {
	        	throwInternalException(ex, errorCode, ex.getClass().getSimpleName().concat("-").concat(errorType), ((HttpClientErrorException) ex).getResponseBodyAsString(), rootCause, clientExceptionSeverity, ((HttpClientErrorException) ex).getStatusCode());
	        } else if (ex instanceof HttpServerErrorException) {
	        	throwInternalException(ex, errorCode, ex.getClass().getSimpleName().concat("-").concat(errorType), ((HttpServerErrorException) ex).getResponseBodyAsString(), rootCause, serverExceptionSeverity, ((HttpServerErrorException) ex).getStatusCode());
	        } else {
	        	throwInternalException(errorCode.APPLICATION_ERROR, ex.getClass().getSimpleName().concat("-").concat(errorType), ex.getMessage(), null, Severity.HIGH, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	   
	   /**
	     * Method to throw abcRuntimeException with abcErrortype, message, severity, rootcause and HttpStatus
	     *
	     * @param errorType  Error Type
	     * @param message    Exception message
	     * @param severity   Severity level
	     * @param rootCause  Root Cause of the Exception
	     * @param httpStatus HTTPStatus code
	     */
	    public void throwInternalException(ErrorCode errorCode, String errorType, String message, String rootCause, Severity severity, HttpStatus httpStatus) {
	        throw new TravelPlanRunTimeExcep(errorCode, errorType, message, severity, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))), rootCause, httpStatus);
	    }

	    /**
	     * Method to throw abcRuntimeException with underlying actual exception and custom Http status
	     *
	     * @param errorType  Error type
	     * @param message    Exception message
	     * @param severity   Severity level
	     * @param httpStatus HTTPStatus code
	     * @param ex         Actual Exception
	     */
	    protected void throwInternalException(Throwable ex, ErrorCode errorCode, String errorType, String message, String rootCause, Severity severity, HttpStatus httpStatus) {
	        throw new TravelPlanRunTimeExcep(errorCode, errorType, message, severity, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))), rootCause, httpStatus, ex);
	    }
}
