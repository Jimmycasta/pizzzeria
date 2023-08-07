package com.jimmycasta.pizzeria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(customizedRequests -> {
                    customizedRequests
                            .requestMatchers(HttpMethod.GET,"/api/pizzas/**").hasAnyRole("ADMIN","CUSTOMER") //Permite GET a varios Roles (ADMIN, CUSTOMER)al pattern especificado.
                            .requestMatchers(HttpMethod.POST, "/api/pizzas/**").hasRole("ADMIN") // Permite POST a un solo Role (ADMIN) en el pattern especificado
                            .requestMatchers(HttpMethod.PUT).hasRole("ADMIN") // Solo el usuario (ADMIN) puede usar el método PUT, todos los pattern.
                            //.requestMatchers("/api/orders/random").hasAuthority("random_order") //Se agrega un permiso de tipo authorities llamado "random_order".
                            .requestMatchers("/api/orders/**").hasRole("ADMIN")
                            //.requestMatchers(HttpMethod.GET, "/api/pizzas/**").permitAll() //Permite el método GET a todos, el en pattern.
                            //.requestMatchers(HttpMethod.PUT).denyAll()  //Deniega el método PUT a todos

                            .anyRequest()
                            .authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
