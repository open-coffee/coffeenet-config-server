package coffee.synyx.config.oauth;

import org.hibernate.validator.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.validation.annotation.Validated;


/**
 * @author  Tobias Schneider
 */
@Validated
@ConfigurationProperties(prefix = "config-server.resource")
public class ResourceProperties {

    @NotEmpty
    private String id;

    public String getId() {

        return id;
    }


    public void setId(String id) {

        this.id = id;
    }
}
