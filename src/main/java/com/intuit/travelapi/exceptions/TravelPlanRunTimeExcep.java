package com.intuit.travelapi.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;



import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.travelapi.entity.ErrorCode;
import com.intuit.travelapi.entity.Severity;

public class TravelPlanRunTimeExcep extends RuntimeException {

	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonPropertyDescription(value="Error code")
	    private ErrorCode code;
	    private String error;
	    private Severity severity;
	    private Date timestamp;
	    @JsonPropertyDescription(value="Defines the reason why abc triggered this exception and indicates the ways to fix it if possible")
	    private String rootCause;
	    @JsonIgnore
	    private HttpStatus exceptionHttpStatus;
	    private Throwable actualException;

    public String getRootCause() {
			return rootCause;
		}

		public void setRootCause(String rootCause) {
			this.rootCause = rootCause;
		}

		public HttpStatus getExceptionHttpStatus() {
			return exceptionHttpStatus;
		}

		public void setExceptionHttpStatus(HttpStatus exceptionHttpStatus) {
			this.exceptionHttpStatus = exceptionHttpStatus;
		}

		public Throwable getActualException() {
			return actualException;
		}

		public void setActualException(Throwable actualException) {
			this.actualException = actualException;
		}



    public TravelPlanRunTimeExcep (String message) {
        super(message);
    }

    public TravelPlanRunTimeExcep (String message, Throwable cause) {
        super(message, cause);
    }

    public TravelPlanRunTimeExcep(ErrorCode code, String error, String message, Severity severity, Date timestamp, String rootCause, HttpStatus exceptionHttpStatus, Throwable ex) {
        super(message);
        this.code = code;
        this.error = error;
        this.severity = severity;
        this.timestamp = timestamp;
        this.rootCause = rootCause;
        this.exceptionHttpStatus = exceptionHttpStatus;
        this.actualException = ex;
    }

    public TravelPlanRunTimeExcep(ErrorCode code, String error, String message, Severity severity, Date timestamp, String rootCause, HttpStatus exceptionHttpStatus) {
        super(message);
        this.code = code;
        this.error = error;
        this.severity = severity;
        this.timestamp = timestamp;
        this.rootCause = rootCause;
        this.exceptionHttpStatus = exceptionHttpStatus;
    }
    

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorLogMessage() throws JsonProcessingException {
        Map<String, Object> payload = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        payload.put("code", this.code);
        payload.put("error", this.error);
        payload.put("message", this.getMessage());
        payload.put("severity", this.severity);
        payload.put("timestamp", this.timestamp);
        return objectMapper.writeValueAsString(payload);
    }

}
