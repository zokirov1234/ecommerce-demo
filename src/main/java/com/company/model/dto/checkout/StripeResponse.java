package com.company.model.dto.checkout;

public class StripeResponse {
    private String sessionId;

    public StripeResponse() {
    }

    public StripeResponse(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
