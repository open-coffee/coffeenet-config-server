package coffee.synyx.config.oauth;

import org.hibernate.validator.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.validation.annotation.Validated;


/**
 * @author  Tobias Schneider
 */
@Validated
@ConfigurationProperties(prefix = "config-server.keystore")
public class KeyStoreProperties {

    private boolean enabled = false;

    @NotEmpty
    private String publicKey = "file:coffeenet-public.cert";

    public boolean isEnabled() {

        return enabled;
    }


    public void setEnabled(boolean enabled) {

        this.enabled = enabled;
    }


    public String getPublicKey() {

        return publicKey;
    }


    public void setPublicKey(String publicKey) {

        this.publicKey = publicKey;
    }
}
