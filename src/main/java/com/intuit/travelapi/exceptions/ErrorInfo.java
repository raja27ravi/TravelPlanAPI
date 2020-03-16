package com.intuit.travelapi.exceptions;
import java.util.Date;

import com.intuit.travelapi.entity.ErrorCode;
public class ErrorInfo {

    private Date timestamp;
    private ErrorCode code;
    private String error;
    private String message;
    private String rootCause;
    private String path;

    public ErrorInfo(Date timestamp, ErrorCode code, String error, String message, String path) {
        this.timestamp = timestamp;
        this.code = code;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public ErrorInfo(Date timestamp, ErrorCode code, String error, String message, String rootCause, String path) {
        this.timestamp = timestamp;
        this.code = code;
        this.error = error;
        this.message = message;
        this.rootCause = rootCause;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }
}
