package tn.iit.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Désactiver CSRF pour simplifier (optionnel)
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/register").permitAll() // Autoriser la page de connexion et d'inscription
                                .anyRequest().authenticated() // Les autres pages nécessitent une authentification
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login") // Page de login personnalisée
                                .defaultSuccessUrl("/compte/",true) // Redirection après connexion
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .permitAll()
                );
        return http.build();
    }

    // Encoder les mots de passe avec BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Définir un UserDetailsService personnalisé (si nécessaire)
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
    }
}