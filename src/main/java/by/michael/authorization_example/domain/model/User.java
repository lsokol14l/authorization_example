package by.michael.authorization_example.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {
  private final String username;
  private final String password;
  private final Role role;

  public boolean hasRole(Role role) {
    return this.role == role;
  }

  public boolean isAdmin() {
    return role == Role.ROLE_ADMIN;
  }

  public boolean isEngineer() {
    return role == Role.ROLE_ENGINEER;
  }

  public boolean isUser() {
    return role == Role.ROLE_USER;
  }
}
