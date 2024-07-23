package com.app.config;

import com.app.config.filter.JwtTokenValidator;
import com.app.service.UserDetailServiceImpl;
import com.app.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

// -- -- -- CONFIGURATION MANUAL  ENDPOINTS --
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http ->{
                    // Config ENDPOINTS - Publicos
                    http.requestMatchers(HttpMethod.POST,"/auth/**").permitAll();
                    // Config ENDPOINTS - Privados
                    http.requestMatchers(HttpMethod.GET,"/method/pruebas").hasAnyAuthority("READ");
                    http.requestMatchers(HttpMethod.POST, "/method/pruebas").hasAnyRole("ADMIN","DEVELOPER");;
                    http.requestMatchers(HttpMethod.PATCH,"/method/pruebas").hasAuthority("REFACTOR");
                    http.requestMatchers(HttpMethod.PUT,"/method/pruebas").hasAuthority("UPDATE");
                    http.requestMatchers(HttpMethod.DELETE,"/method/pruebas").hasAuthority("DELETE");
                    // Config ENDPOINTS - no especificados
                    http.anyRequest().denyAll();
                    //http.anyRequest().authenticated();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean       // return NoOpPasswordEncoder.getInstance(); // -- NO RECOMENDADO -- (txt plano)
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

// -- VER CONTRASEÑA ENCRIPTADA
//    public static void main(String[] args){
//        System.out.println(new BCryptPasswordEncoder().encode("admin"));
//    }


