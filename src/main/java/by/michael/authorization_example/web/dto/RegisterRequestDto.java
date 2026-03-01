package by.michael.authorization_example.web.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
  private String login;
  private String password;
}
