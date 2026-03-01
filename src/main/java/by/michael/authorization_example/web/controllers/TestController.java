package by.michael.authorization_example.web.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {
  @GetMapping("/welcome")
  public String welcome() {
    return "this is unprotected page";
  }

  @GetMapping("/user")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public String pageForUser() {
    return "this is page for users";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String pageForAdmin() {
    return "this is page for admins";
  }

  @GetMapping("/engineer")
  @PreAuthorize("hasAuthority('ROLE_ENGINEER')")
  public String pageForEngineer() {
    return "This is a page for engineers with ROLE_ENGINEER";
  }

  @GetMapping("/all")
  @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_ENGINEER')")
  public String pageForAll() {
    return "This is a page for all authenticated employees";
  }
}
