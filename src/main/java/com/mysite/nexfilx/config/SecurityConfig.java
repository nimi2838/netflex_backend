package com.mysite.nexfilx.config;

import com.mysite.nexfilx.User.dao.UserRepository;
import com.mysite.nexfilx.auth.jwt.JwtAuthenticationFilter;
import com.mysite.nexfilx.auth.jwt.JwtAuthorizationFilter;
import com.mysite.nexfilx.config.handler.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션을 사용하지 않음.
                .and()
                .formLogin()

                .and()
                .httpBasic().disable()
                .apply(new CustomDsl())
                .and()
                .authorizeRequests( authorize -> authorize
                                .antMatchers(HttpMethod.POST,"/api/v1/getLastPayDate")
                                .hasAnyRole("USER", "ADMIN")
                                .antMatchers(HttpMethod.GET,"/api/v1/getProfile")
                                .hasAnyRole("USER","ADMIN")
                                .antMatchers(HttpMethod.GET, "/origin")
                                .hasAnyRole("USER", "ADMIN")
                                .antMatchers(HttpMethod.GET, "/browse/**")
                                .hasAnyRole("USER", "ADMIN")
                                .antMatchers(HttpMethod.POST, "/browse/my-list")
                                .hasAnyRole("USER", "ADMIN")
                                .antMatchers(HttpMethod.GET, "/rank")
                                .hasAnyRole("USER", "ADMIN")
                                .antMatchers(HttpMethod.GET, "/search/**")
                                .hasAnyRole("USER", "ADMIN")
                                .antMatchers(HttpMethod.GET, "/allcontent")
                                .hasAnyRole("USER", "ADMIN")
                                .antMatchers(HttpMethod.POST, "/getorder")
                                .hasAnyRole("USER", "ADMIN")
                                .antMatchers(HttpMethod.POST, "/order")
                                .hasAnyRole("USER", "ADMIN")
                                .anyRequest()
                                .permitAll()
                                .and()
//                .and()
//                .loginPage("/user/login")
//                .defaultSuccessUrl("/")
//                .and()
//                .authorizeRequests(authorize -> authorize
//                        .anyRequest()
//                        .permitAll()
                )
        .build();
    }

    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            System.out.println("authenticationManager : " + authenticationManager);
            http
                    .addFilter(new JwtAuthenticationFilter(authenticationManager, userRepository))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
        }
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("Authorization");



        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
