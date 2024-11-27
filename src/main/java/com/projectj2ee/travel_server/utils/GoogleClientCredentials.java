package com.projectj2ee.travel_server.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GoogleClientCredentials {
    @JsonProperty("web")
    private Installed installed;

    @Getter
    public static class Installed {
        @JsonProperty("client_id")
        private String clientId;

        @JsonProperty("client_secret")
        private String clientSecret;

        @JsonProperty("auth_uri")
        private String authUri;

        @JsonProperty("token_uri")
        private String tokenUri;

        @JsonProperty("auth_provider_x509_cert_url")
        private String authProviderX509CertUrl;

        @JsonProperty("redirect_uris")
        private String[] redirectUris;

        @JsonProperty("project_id")
        private String projectId;
    }
}
