package by.michael.authorization_example.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping("/")
  public String home() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    String roles = auth.getAuthorities().toString();

    return String.format(
        "Welcome, %s! Your roles: %s\n\nAvailable endpoints:\n"
            + "- /test/welcome (public)\n"
            + "- /test/users (ROLE_USER)\n"
            + "- /test/admins (ROLE_ADMIN)\n"
            + "- /test/engineers (ROLE_ENGINEER)\n"
            + "- /test/all (any authenticated)",
        username, roles);
  }
}
