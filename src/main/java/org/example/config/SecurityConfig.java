package org.example.config;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.config.oauth2.FormLoginFailureHandler;
import org.example.service.PrincipalOauth2UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c->c.disable());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll())
                .formLogin(formLogin -> formLogin.loginPage("/login")
                        .loginProcessingUrl("/loginProcessing")
                        .usernameParameter("userEmail")
                        .passwordParameter("userPassword")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(logout->logout.logoutSuccessUrl("/")
                        .logoutUrl("/logout")
                        .deleteCookies("remember-me"));

        return http.build();
    }
}
