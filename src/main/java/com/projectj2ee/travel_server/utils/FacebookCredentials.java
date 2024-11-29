package com.projectj2ee.travel_server.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class FacebookCredentials {
    @JsonProperty("facebook")
    private Installed installed;


    @Getter
    public static class Installed {
        @JsonProperty("client_id")
        private String clientId;

        @JsonProperty("client_secret")
        private String clientSecret;

        @JsonProperty("redirect-uri")
        private String redirectUri;

        @JsonProperty("token_uri")
        private String tokenUri;

        @JsonProperty("auth_uri")
        private String authUri;

        @JsonProperty("user-info-uri")
        private String userInfoUri;
    }
}
