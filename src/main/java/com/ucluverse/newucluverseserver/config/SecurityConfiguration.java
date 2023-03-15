package com.ucluverse.newucluverseserver.config;

import com.ucluverse.newucluverseserver.domain.auth.OAuth2SuccessHandler;
import com.ucluverse.newucluverseserver.domain.auth.PrincipalOAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final PrincipalOAuth2DetailsService principalOAuth2DetailsService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2Login()
                .userInfoEndpoint().userService(principalOAuth2DetailsService)
                .and()
                .successHandler(oAuth2SuccessHandler);
        return http.build();
    }
}

/*
@Configuration
@EnableWebSecurity
public class AuthorizeUrlsSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/**").hasRole("USER")
                .and()
                .formLogin();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}

    We can also configure multiple URLs. The configuration below requires authentication to every URL and will grant access to URLs starting with /admin/ to only the "admin" user. All other URLs either user can access.
@Configuration
@EnableWebSecurity
public class AuthorizeUrlsSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/**").hasRole("USER")
                .and()
                .formLogin();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}

    Note that the matchers are considered in order. Therefore, the following is invalid because the first matcher matches every request and will never get to the second mapping:
@Configuration
@EnableWebSecurity
public class AuthorizeUrlsSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/**").hasRole("USER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin();
        return http.build();
    }
}
*/
