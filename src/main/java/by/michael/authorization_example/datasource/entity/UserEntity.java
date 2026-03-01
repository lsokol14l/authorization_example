package by.michael.authorization_example.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID uuid;

  @Column(name = "login", unique = true, nullable = false)
  private String login;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "role", nullable = false)
  private String role;
}
