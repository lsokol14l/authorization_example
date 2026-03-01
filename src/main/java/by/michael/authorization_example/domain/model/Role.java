package by.michael.authorization_example.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
  ROLE_USER("ROLE_USER"),
  ROLE_ADMIN("ROLE_ADMIN"),
  ROLE_ENGINEER("ROLE_ENGINEER");

  private final String authority;

  public static Role fromString(String role) {
    for (Role r : Role.values()) {
      if (r.authority.equals(role)) {
        return r;
      }
    }
    throw new IllegalArgumentException("Unknown role: " + role);
  }
}
