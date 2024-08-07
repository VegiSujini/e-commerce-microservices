package com.e_commerce.e_commerce_microservices.entity;

public class LoginResponse {

    private String token;
    private String mfaSecretKey;

    public LoginResponse() {
    }

    public LoginResponse(String token, String mfaSecretKey) {
        this.token = token;
        this.mfaSecretKey = mfaSecretKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMfaSecretKey() {
        return mfaSecretKey;
    }

    public void setMfaSecretKey(String mfaSecretKey) {
        this.mfaSecretKey = mfaSecretKey;
    }
}
