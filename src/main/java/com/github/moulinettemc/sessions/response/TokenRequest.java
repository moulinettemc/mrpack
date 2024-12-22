package com.github.moulinettemc.sessions.response;

public class TokenRequest {
    public String error = null;
    public String token = null;

    @Override
    public String toString() {
        return "TokenRequest{" +
                "error='" + error + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
