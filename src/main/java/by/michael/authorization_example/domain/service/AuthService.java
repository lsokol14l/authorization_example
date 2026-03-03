package by.michael.authorization_example.domain.service;

import by.michael.authorization_example.datasource.entity.UserEntity;
import by.michael.authorization_example.datasource.repository.UserRepository;
import by.michael.authorization_example.domain.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  /** Регистрация нового пользователя */
  @Transactional
  public void register(String login, String password) {
    // 1. Проверяем: существует ли пользователь?
    if (userRepository.existsByLogin(login)) {
      throw new IllegalArgumentException("Пользователь с таким логином уже существует");
    }

    // 2. Хешируем пароль
    String hashedPassword = passwordEncoder.encode(password);

    // 3. Создаем пользователя
    UserEntity user = new UserEntity();
    user.setLogin(login);
    user.setPassword(hashedPassword);
    user.setRole("ROLE_USER");

    // 4. Сохраняем в БД
    userRepository.save(user);
  }
}
