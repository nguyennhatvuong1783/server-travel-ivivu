package com.projectj2ee.travel_server.service.config;

import com.projectj2ee.travel_server.utils.GoogleClientCredentials;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
@AllArgsConstructor
public class ClientRegistrationRepositoryConfig {
    @Autowired
    private final GoogleClientCredentials googleClientCredentials;

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration googleClient = ClientRegistration
                .withRegistrationId("google")
                .clientId(googleClientCredentials.getInstalled().getClientId())
                .clientSecret(googleClientCredentials.getInstalled().getClientSecret())
                .scope("openid", "profile", "email")
                .authorizationUri(googleClientCredentials.getInstalled().getAuthProviderX509CertUrl())
                .tokenUri(googleClientCredentials.getInstalled().getTokenUri())
                .userInfoUri("https://openidconnect.googleapis.com/v1/userinfo")
                .userNameAttributeName("sub")
                .clientName("Google")
                .redirectUri(googleClientCredentials.getInstalled().getRedirectUris()[1])
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .build();

        return new InMemoryClientRegistrationRepository(googleClient);
    }

}
