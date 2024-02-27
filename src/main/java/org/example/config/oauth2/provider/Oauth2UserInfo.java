package org.example.config.oauth2.provider;

import java.util.Map;

public interface Oauth2UserInfo {
    Map<String, Object> getAttributes();
    String getProviderId();
    String getProvider();
    String getUserEmail();
    String getName();
}
