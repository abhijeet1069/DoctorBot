package com.servion.doctorBot.message.pojo;

// Using a feature based package system not type based.
public class MessagePOJO {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	@Override
	public String toString() {
		return "MessagePOJO [message=" + message + "]";
	}
}
