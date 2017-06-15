package coffee.synyx.config.oauth;

import org.hibernate.validator.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.validation.annotation.Validated;


/**
 * @author  Tobias Schneider
 */
@Validated
@ConfigurationProperties(prefix = "coffeenet.resource")
public class ResourceProperties {

    @NotEmpty
    private String id;

    @NotEmpty
    private String publicKey = "coffeenet-public.cert";

    public String getId() {

        return id;
    }


    public void setId(String id) {

        this.id = id;
    }


    public String getPublicKey() {

        return publicKey;
    }


    public void setPublicKey(String publicKey) {

        this.publicKey = publicKey;
    }
}
