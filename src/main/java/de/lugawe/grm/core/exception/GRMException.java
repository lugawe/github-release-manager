package de.lugawe.grm.core.exception;

public class GRMException extends RuntimeException {

    public GRMException() {
        super();
    }

    public GRMException(String message) {
        super(message);
    }

    public GRMException(String message, Throwable cause) {
        super(message, cause);
    }

    public GRMException(Throwable cause) {
        super(cause);
    }
}
