package cz.pollib.service.common.model;

import cz.pollib.constant.ErrorCode;

import java.util.Arrays;
import java.util.List;

public class ErrorResponse {
    private ErrorCode code;
    private String uri;
    private List<String> errors;

    public ErrorResponse(ErrorCode code, String uri, List<String> errors) {
        this.uri = uri;
        this.code = code;
        this.errors = errors;
    }

    public ErrorResponse(ErrorCode code, String uri, String error) {
        this.uri = uri;
        this.code = code;
        errors = Arrays.asList(error);
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
