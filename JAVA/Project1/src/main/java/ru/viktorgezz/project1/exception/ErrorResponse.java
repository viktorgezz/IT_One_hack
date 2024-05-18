package ru.viktorgezz.project1.exception;

public class ErrorResponse {

    private String message;
    private long timestamp;

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis() / (1000 * 60);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
