package by.michael.authorization_example.domain.service;

import by.michael.authorization_example.datasource.mapper.UserMapper;
import by.michael.authorization_example.datasource.repository.UserRepository;
import by.michael.authorization_example.domain.model.SecurityUser;
import by.michael.authorization_example.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        userRepository
            .findByLogin(username)
            .map(userMapper::toDomain)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    return new SecurityUser(user);
  }
}
