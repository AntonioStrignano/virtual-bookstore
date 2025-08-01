package it.books.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                // Pubblico
                .requestMatchers("/", "/login", "/register", "/css/**", "/js/**").permitAll()
                // Solo ADMIN
                .requestMatchers("/users/**", "/analytics/**", "/analytic-types/**").hasAuthority("ADMIN")
                // ADMIN e SHOP ASSISTANT
                .requestMatchers(
                        "/shop-assistants/**",
                        "/inventory/**",
                        "/inv-status/**",
                        "/discounts/**",
                        "/discount-types/**",
                        "/warehouse/**",
                        "/notification-types/**"
                ).hasAnyAuthority("ADMIN", "SHOP_ASSISTANT")
                // Tutti loggati (ma controller e service devono filtrare per owner)
                .requestMatchers(
                        "/customers/**",
                        "/carts/**",
                        "/wishlists/**",
                        "/orders/**",
                        "/reviews/**",
                        "/notifications/**",
                        "/search-history/**"
                ).hasAnyAuthority("ADMIN", "SHOP_ASSISTANT", "CUSTOMER")
                // Tutti possono vedere libri e anagrafiche
                .requestMatchers(
                        "/books/**",
                        "/authors/**",
                        "/awards/**",
                        "/collections/**",
                        "/editions/**",
                        "/formats/**",
                        "/genres/**",
                        "/publishers/**"
                ).permitAll()
                // Tutto il resto richiede autenticazione
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    DatabaseUserDetailService userDetailsService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }

    @Bean
    DaoAuthenticationProvider authenticatorProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
