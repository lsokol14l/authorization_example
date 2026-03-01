package by.michael.authorization_example.web.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test/")
@AllArgsConstructor
public class TestController {
  @GetMapping("/welcome")
  public String welcome() {
    return "this is unprotected page";
  }

  @GetMapping("/users")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public String pageForUser() {
    return "this is page for users";
  }

  @GetMapping("/admins")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String pageForAdmin() {
    return "this is page for admins";
  }

  @GetMapping("/all")
  public String pageForAll() {
    return "this is page for all employees";
  }
}
