package com.innowise.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.innowise.model.Role.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final UserRepositoryUserDetailsService userRepositoryUserDetailsService;
    private final SuccessHandler successHandler;

    @Autowired
    public SecurityConfiguration(UserRepositoryUserDetailsService userRepositoryUserDetailsService, SuccessHandler successHandler) {
        this.userRepositoryUserDetailsService = userRepositoryUserDetailsService;
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
                .antMatchers("/userpool", "/userpool/user/{id}")
                .hasAnyAuthority(ADMIN.name(), USER.name())
                .antMatchers("/registration", "/userpool/update/{id}")
                .hasAnyAuthority(ADMIN.name())
//                .antMatchers("/registration")
//                .permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .authenticationProvider(authenticationProvider())
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(successHandler)
            .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic()
            .and()
                .csrf()
                .disable();

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userRepositoryUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(encoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
