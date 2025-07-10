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
        http.authorizeHttpRequests()
                /*		.requestMatchers("/categorie", "/categorie/**").hasAuthority("ADMIN")
        *		.requestMatchers("/admin", "/admin/**").hasAuthority("ADMIN")
        * 		.requestMatchers("/note/**").hasAnyAuthority("ADMIN", "USER")
        *		.requestMatchers("/tickets/create", "/tickets/edit/**", "/tickets/delete/**").hasAuthority("ADMIN")
        *		.requestMatchers("/tickets/stato/**").hasAnyAuthority("ADMIN", "USER")
        *		.requestMatchers("/user/create").hasAuthority("ADMIN")
        *		.requestMatchers("/user/update/**", "/user/flag/**").hasAnyAuthority("ADMIN", "USER")*/
                .requestMatchers("/**")
                .permitAll().and().formLogin().and().logout().and().exceptionHandling().and().csrf().disable();

        //.requestMatchers("/.../**).hasAuthority("ROLE_NAME").reqMa...
        return http.build();
    }

    @Bean
    DatabaseUserDetailService userDetailsService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticatorProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
