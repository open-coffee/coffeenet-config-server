package coffee.synyx.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.Resource;

import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import org.springframework.util.FileCopyUtils;

import java.io.IOException;

import java.nio.charset.Charset;


/**
 * @author  Tobias Schneider - schneider@synyx.de
 */
@Configuration
@EnableConfigurationProperties(KeyStoreProperties.class)
class TokenStoreConfiguration {

    private final ApplicationContext context;
    private final KeyStoreProperties keyStoreProperties;

    @Autowired
    TokenStoreConfiguration(ApplicationContext context, KeyStoreProperties keyStoreProperties) {

        this.context = context;
        this.keyStoreProperties = keyStoreProperties;
    }

    @Bean
    @Autowired
    TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {

        return new JwtTokenStore(jwtAccessTokenConverter);
    }


    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter() {

        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        if (keyStoreProperties.isEnabled()) {
            final Resource resource = context.getResource(keyStoreProperties.getPublicKey());

            String publicKey;

            try {
                publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()),
                        Charset.forName("UTF-8"));
            } catch (IOException e) {
                throw new PublicKeyException("Could not retrieve the public CoffeeNet key", e);
            }

            converter.setVerifierKey(publicKey);
        }

        return converter;
    }
}
