package org.example.config.oauth2;

import lombok.RequiredArgsConstructor;
import org.example.config.oauth2.provider.GoogleUserInfo;
import org.example.config.oauth2.provider.KakaoUserInfo;
import org.example.config.oauth2.provider.NaverUserInfo;
import org.example.config.oauth2.provider.Oauth2UserInfo;
import org.example.model.entity.User;
import org.example.model.enums.Role;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User =  super.loadUser(userRequest);
        Oauth2UserInfo oauth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();    //google 또는 naver

        if (provider.equals("google")) {
            oauth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (provider.equals("naver")) {
            oauth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        } else if (provider.equals("kakao")) {
            oauth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

        String email = oauth2UserInfo.getUserEmail();
        String tempName = oauth2UserInfo.getProvider() + "_" + UUID.randomUUID().toString().substring(0,6);
        String password = bCryptPasswordEncoder.encode("password" + UUID.randomUUID().toString().substring(0,6));

        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            User newUser = User.builder()
                    .user_email(email)
                    .user_password(password)
                    .user_name(tempName)
                    .user_role(Role.USER)
                    .build();
            userRepository.save(newUser);
        }
        return new PrincipalDetails(user, oauth2UserInfo);
    }
}
