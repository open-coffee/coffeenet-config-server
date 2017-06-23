package coffee.synyx.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;


/**
 * @author  Tobias Schneider - schneider@synyx.de
 */
@Configuration
@EnableResourceServer
@EnableConfigurationProperties(ResourceProperties.class)
class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final TokenStore tokenStore;
    private final ResourceProperties resourceProperties;

    @Autowired
    ResourceServerConfiguration(TokenStore tokenStore, ResourceProperties resourceProperties) {

        this.tokenStore = tokenStore;
        this.resourceProperties = resourceProperties;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests().anyRequest().authenticated();
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        resources.resourceId(resourceProperties.getId()).tokenStore(tokenStore);
    }
}
