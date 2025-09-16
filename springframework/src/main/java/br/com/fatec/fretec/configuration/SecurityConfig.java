package br.com.fatec.fretec.configuration;

import br.com.fatec.fretec.security.JwtAuthFilterSecurity;
import br.com.fatec.fretec.security.JwtSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity httpSecurity,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder builder = httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsService).
                passwordEncoder(passwordEncoder);
        return builder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(
            JwtSecurity jwtSecurity,
            HttpSecurity httpSecurity,
            UserDetailsService userDetails) throws Exception {
        JwtAuthFilterSecurity jwtFilter = new JwtAuthFilterSecurity(jwtSecurity, userDetails);
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                 "/v3/api-docs/**",
                                 "/swagger-ui.html",
                                "/fretec/v1/user/save/**",
                                "/fretec/v1/auth/forget/**",
                                "/fretec/v1/auth/login/**")
                        .permitAll()
//                        .requestMatchers("/fretec/v1/auth/save/**")
//                        .hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }


}
