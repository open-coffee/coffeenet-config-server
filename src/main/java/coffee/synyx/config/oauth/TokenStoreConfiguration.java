package coffee.synyx.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


/**
 * @author  Tobias Schneider - schneider@synyx.de
 */
@Configuration
public class TokenStoreConfiguration {

    @Bean
    @Autowired
    public TokenStore tokenStore(JwtAccessTokenConverter jwtTokenEnhancer) {

        return new JwtTokenStore(jwtTokenEnhancer);
    }


    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");

        return converter;
    }
}
