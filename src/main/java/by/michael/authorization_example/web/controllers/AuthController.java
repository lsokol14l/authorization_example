package by.michael.authorization_example.web.controllers;

import by.michael.authorization_example.domain.model.SecurityUser;
import by.michael.authorization_example.domain.service.AuthService;
import by.michael.authorization_example.web.dto.AuthResponseDto;
import by.michael.authorization_example.web.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  /**
   * Регистрация нового пользователя
   */
  @PostMapping("/register")
  public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto request) {
    try {
      authService.register(request.getLogin(), request.getPassword());

      return ResponseEntity.ok(
              new AuthResponseDto(true, "Регистрация успешна! Теперь войдите в систему."));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(new AuthResponseDto(false, e.getMessage()));
    }
  }

  /**
   * Логин - проверка учетных данных
   *
   * ⚠️ ВАЖНО: Frontend отправляет Authorization: Basic заголовок
   * Spring Security АВТОМАТИЧЕСКИ декодирует его и создает Authentication
   *
   * @param authentication - Spring Security автоматически внедряет после проверки пароля
   * @return данные пользователя
   */
  @PostMapping("/login")
  public ResponseEntity<?> login(Authentication authentication) {
    // 1. Spring Security УЖЕ проверил:
    //    - Декодировал "Basic base64(login:password)"
    //    - Вызвал userDetailsService.loadUserByUsername(login)
    //    - Проверил пароль через passwordEncoder.matches()
    //    - Создал Authentication объект
    //    - Если мы здесь - значит пароль ВЕРНЫЙ!

    // 2. Извлекаем данные пользователя
    SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();

    // 3. Возвращаем данные клиенту
    return ResponseEntity.ok(Map.of(
            "success", true,
            "username", securityUser.getUsername(),
            "role", securityUser.getUser().getRole().name()
    ));
  }
}