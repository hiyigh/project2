package org.example.config.oauth2.provider;

import java.util.Map;

public class NaverUserInfo implements Oauth2UserInfo{
    private Map<String, Object> attributes;
    private Map<String, Object> attributesResponse;
    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.attributesResponse = (Map<String, Object>) attributes.get("response");

    }
    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public String getProviderId() {
        return this.attributesResponse.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "Naver";
    }

    @Override
    public String getUserEmail() {
        return this.attributesResponse.get("email").toString();
    }

    @Override
    public String getName() {
        return this.attributesResponse.get("name").toString();
    }
}
