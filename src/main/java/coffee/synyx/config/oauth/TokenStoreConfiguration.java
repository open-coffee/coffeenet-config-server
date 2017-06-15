package coffee.synyx.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import org.springframework.util.FileCopyUtils;

import java.io.IOException;


/**
 * @author  Tobias Schneider - schneider@synyx.de
 */
@Configuration
@EnableConfigurationProperties(ResourceProperties.class)
public class TokenStoreConfiguration {

    private final ResourceProperties resourceProperties;

    @Autowired
    public TokenStoreConfiguration(ResourceProperties resourceProperties) {

        this.resourceProperties = resourceProperties;
    }

    @Bean
    @Autowired
    public TokenStore tokenStore(JwtAccessTokenConverter jwtTokenEnhancer) {

        return new JwtTokenStore(jwtTokenEnhancer);
    }


    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource(resourceProperties.getPublicKey());
        String publicKey;

        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            throw new PublicKeyException("Could not retrieve the public CoffeeNet key", e);
        }

        converter.setVerifierKey(publicKey);

        return converter;
    }
}
