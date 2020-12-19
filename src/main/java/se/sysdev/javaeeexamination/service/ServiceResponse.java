package se.sysdev.javaeeexamination.service;

public class ServiceResponse {
    private boolean hasErrors;
    private String message;

    public boolean hasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
