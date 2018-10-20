package br.com.acoaapi.exception;

public class ApplicationException extends RuntimeException {

    private Integer statusCode;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ApplicationException(Throwable cause, String message, Integer statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
