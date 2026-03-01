package by.michael.authorization_example.web.controllers;

import by.michael.authorization_example.domain.service.AuthService;
import by.michael.authorization_example.web.dto.AuthResponseDto;
import by.michael.authorization_example.web.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // ← ВАЖНО: /api/auth, не просто /auth
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  /**
   * Регистрация нового пользователя
   *
   * @param request {login, password}
   * @return {success: true, message: "..."}
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
}
