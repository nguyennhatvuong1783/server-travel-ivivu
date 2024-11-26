package com.projectj2ee.travel_server.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleClientCredentials {
    @JsonProperty("web")
    private Installed installed;

    public static class Installed {
        private String clientId;
        private String clientSecret;
        private String authUri;
        private String tokenUri;
        private String authProviderX509CertUrl;
        private String[] redirectUris;
    }
}
