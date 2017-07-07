package dev.sol.catalog;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by solo on 6/8/2017.
 */
public class OAuth2Config {

    @NotEmpty
    @JsonProperty
    private String redirectUrl;

    @NotEmpty
    @JsonProperty
    private String scope;

    @NotEmpty
    @JsonProperty
    private String clientId;

    @NotEmpty
    @JsonProperty
    private String clientSecret;

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getScope() {
        return scope;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

}
