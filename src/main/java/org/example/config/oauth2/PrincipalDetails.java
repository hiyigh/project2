package org.example.config.oauth2;

import lombok.Getter;
import org.example.config.oauth2.provider.Oauth2UserInfo;
import org.example.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
public class PrincipalDetails implements UserDetails, OAuth2User {
    private User user;
    private Oauth2UserInfo oauth2UserInfo;
    public PrincipalDetails(User user) {
        this.user = user;
    }
    public PrincipalDetails(User user, Oauth2UserInfo oauth2UserInfo) {
        this.user = user;
        this.oauth2UserInfo = oauth2UserInfo;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getUser_role().toString();
            }
        });
        return null;
    }
    @Override
    public String getName() {
        return null;
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }



    @Override
    public String getPassword() {
        return user.getUser_password();
    }
    /**
     * UserDetails 구현
     * PK값을 반환해준다
     */
    @Override
    public String getUsername() {
        return user.getUser_email();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
