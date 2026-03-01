package by.michael.authorization_example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {
  private boolean success;
  private String message;
}
