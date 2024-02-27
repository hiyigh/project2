package org.example.config.oauth2.provider;

import java.util.Map;

public class GoogleUserInfo implements Oauth2UserInfo{
    private Map<String, Object> attributes;
    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;

    }
    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public String getProviderId() {
        return this.attributes.get("sub").toString();
    }

    @Override
    public String getProvider() {
        return "Google";
    }

    @Override
    public String getUserEmail() {
        return this.attributes.get("email").toString();
    }

    @Override
    public String getName() {
        return this.attributes.get("name").toString();
    }
}
