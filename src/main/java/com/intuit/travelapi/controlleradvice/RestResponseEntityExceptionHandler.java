package com.intuit.travelapi.controlleradvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.travelapi.entity.ErrorCode;
import com.intuit.travelapi.entity.Severity;
import com.intuit.travelapi.exceptions.ErrorInfo;
/*import com.ingka.abc.audit.constants.ApplicationConstants;
import com.ingka.abc.audit.constants.Severity;
import com.ingka.abc.audit.errorhandler.exception.ResourceNotFoundException;
import com.ingka.abc.audit.errorhandler.exception.TravelPlanRunTimeExcep;
import com.ingka.abc.audit.model.AuditInfo;
import com.ingka.abc.audit.model.DirectionEnum;
import com.ingka.abc.audit.model.ErrorInfo;
import com.ingka.abc.audit.model.ErrorCode;
import com.ingka.abc.audit.util.AuditUtil;*/
import com.intuit.travelapi.exceptions.TravelPlanRunTimeExcep;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.xml.sax.SAXException;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.Objects;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	// private AuditUtil auditUtil;
	private ObjectMapper objectMapper;

	// TODO: Keep updating exception List

	@Autowired
	public RestResponseEntityExceptionHandler(ObjectMapper objectMapper) {
		// this.auditUtil = auditUtil;
		this.objectMapper = objectMapper;
	}

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleIllegalArgument(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Illegal Argument Exception occured" + ex.getMessage();
		TravelPlanRunTimeExcep exception = new TravelPlanRunTimeExcep(ErrorCode.DATA_ERROR, "ILLEGAL_ARGUMENT_FOUND",
				bodyOfResponse, Severity.LOW, new Date(), "", HttpStatus.INTERNAL_SERVER_ERROR);
		return handleExceptionInternal(ex, exception, getResponseHeaders(request), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

	/*
	 * // @ExceptionHandler(value = {ResourceNotFoundException.class}) protected
	 * ResponseEntity<Object> handleNotFound(ResourceNotFoundException ex,
	 * WebRequest request) { String bodyOfResponse = "Resource not found " +
	 * ex.getMessage(); TravelPlanRunTimeExcep exception = new
	 * TravelPlanRunTimeExcep(ErrorCode.RESOURCE_ERROR, "RESOURCE_NOT_FOUND",
	 * bodyOfResponse, Severity.LOW, new Date(), "", HttpStatus.NOT_FOUND); return
	 * handleExceptionInternal(ex, exception, getResponseHeaders(request),
	 * HttpStatus.NOT_FOUND, request); }
	 */
	 

	/*
	 * @ExceptionHandler(value = { SAXException.class }) protected
	 * ResponseEntity<Object> handleSaxExceptions(SAXException ex, WebRequest
	 * request) { String bodyOfResponse = ex.getMessage(); TravelPlanRunTimeExcep
	 * exception = new TravelPlanRunTimeExcep(ErrorCode.VALIDATION_ERROR,
	 * "PARSING_ERROR", bodyOfResponse, Severity.LOW, new Date(), "",
	 * HttpStatus.BAD_REQUEST); return handleExceptionInternal(ex, exception,
	 * getResponseHeaders(request), HttpStatus.BAD_REQUEST, request); }
	 */

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String bodyOfResponse = "Message not readable " + ex.getMessage();
		TravelPlanRunTimeExcep exception = new TravelPlanRunTimeExcep(ErrorCode.VALIDATION_ERROR, "PARSING_ERROR",
				bodyOfResponse, Severity.LOW, new Date(), null, HttpStatus.BAD_REQUEST);
		return handleExceptionInternal(ex, exception, getResponseHeaders(request), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { TravelPlanRunTimeExcep.class })
	protected ResponseEntity<Object> handleTravelPlanRunTimeExcep(TravelPlanRunTimeExcep ex, WebRequest request) {
		return handleExceptionInternal(ex, ex, getResponseHeaders(request), ex.getExceptionHttpStatus(), request);
	}

	@ExceptionHandler(value = { RuntimeException.class })
	protected ResponseEntity<Object> handleUnknownException(RuntimeException ex, WebRequest request) {
		TravelPlanRunTimeExcep exception = new TravelPlanRunTimeExcep(ErrorCode.APPLICATION_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR.name(), ex.getMessage(), Severity.HIGH, new Date(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
		return handleExceptionInternal(ex, exception, getResponseHeaders(request), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

	private HttpHeaders getResponseHeaders(WebRequest request) {
		HttpHeaders httpHeaders = new HttpHeaders();
		// httpHeaders.add(ApplicationConstants.INBOUND_APPLICATION_ID,
		// request.getHeader(ApplicationConstants.INBOUND_APPLICATION_ID));
		// httpHeaders.add(ApplicationConstants.INBOUND_APPLICATION_LEGAL_COMPANY,
		// request.getHeader(ApplicationConstants.INBOUND_APPLICATION_LEGAL_COMPANY));
		// httpHeaders.add(ApplicationConstants.TRANSACTION_ID,
		// request.getHeader(ApplicationConstants.TRANSACTION_ID));
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, request.getHeader(HttpHeaders.CONTENT_TYPE));
		return httpHeaders;
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorInfo errorInfo = null;
		if (body instanceof TravelPlanRunTimeExcep) {
			if (Objects.nonNull(((TravelPlanRunTimeExcep) body).getRootCause()))
				errorInfo = new ErrorInfo(((TravelPlanRunTimeExcep) body).getTimestamp(),
						((TravelPlanRunTimeExcep) body).getCode(), ((TravelPlanRunTimeExcep) body).getError(),
						((TravelPlanRunTimeExcep) body).getMessage(), ((TravelPlanRunTimeExcep) body).getRootCause(),
						((ServletWebRequest) request).getRequest().getRequestURI());
			else
				errorInfo = new ErrorInfo(((TravelPlanRunTimeExcep) body).getTimestamp(),
						((TravelPlanRunTimeExcep) body).getCode(), ((TravelPlanRunTimeExcep) body).getError(),
						((TravelPlanRunTimeExcep) body).getMessage(),
						((ServletWebRequest) request).getRequest().getRequestURI());
		} else {
			errorInfo = new ErrorInfo(new Date(), ErrorCode.APPLICATION_ERROR, status.getReasonPhrase(), e.getMessage(),
					((ServletWebRequest) request).getRequest().getRequestURI());
		}
		// if (auditLogger.isErrorEnabled()) {

		// AuditInfo governanceInfo =
		// auditUtil.getAuditInfoFromThreadContext(ThreadContext.get(ApplicationConstants.CURRENT_APPLICATION_ID),
		// DirectionEnum.FAIL, null);
		// auditLogger.error(objectMapper.writeValueAsString(governanceInfo));
		TravelPlanRunTimeExcep exception = null;
		if (e instanceof TravelPlanRunTimeExcep) {
			exception = (TravelPlanRunTimeExcep) e;
		} else if (body instanceof TravelPlanRunTimeExcep) {
			exception = (TravelPlanRunTimeExcep) body;
		} else {
			exception = new TravelPlanRunTimeExcep(errorInfo.getCode(), errorInfo.getError(), errorInfo.getMessage(),
					Severity.MEDIUM, new Date(), null, HttpStatus.BAD_REQUEST);
		}
		// ApplicationConstants.errorLogger.error(exception.getErrorLogMessage(),
		// exception);

		// }
		return super.handleExceptionInternal(e, errorInfo, headers, status, request);
	}
}