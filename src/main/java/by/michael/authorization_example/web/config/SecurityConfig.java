package by.michael.authorization_example.web.config;

import by.michael.authorization_example.domain.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            auth ->
                auth
                    // Публичные эндпоинты
                    .requestMatchers("/", "/api/auth/register", "/api/auth/login")
                    .permitAll()

                    // Защищенные эндпоинты - требуют авторизации
                    .requestMatchers("/api/games/**")
                    .authenticated()
                    .requestMatchers("/api/profile/**")
                    .authenticated()

                    // Админские эндпоинты - требуют роль ADMIN
                    .requestMatchers("/api/admin/**")
                    .hasRole("ADMIN")

                    // Все остальное - требует авторизации
                    .anyRequest()
                    .authenticated())
        .httpBasic(Customizer.withDefaults())
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
