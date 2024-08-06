package com.e_commerce.service;

import org.springframework.stereotype.Service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;

@Service
public class MFAService {
    private final GoogleAuthenticator googleAuthenticator;

    public MFAService() {
        GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder configBuilder = new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                .setTimeStepSizeInMillis(30000) // 30 seconds
                .setWindowSize(3); // allows time window for verification
        this.googleAuthenticator = new GoogleAuthenticator(configBuilder.build());
    }

    public String generateSecretKey() {
        return googleAuthenticator.createCredentials().getKey();
    }

    public boolean verifyCode(String secretKey, int code) {
        return googleAuthenticator.authorize(secretKey, code);
    }
}
