package by.michael.authorization_example.domain.service;

import by.michael.authorization_example.datasource.entity.UserEntity;
import by.michael.authorization_example.datasource.repository.UserRepo;
import java.util.Optional;

import by.michael.authorization_example.domain.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> user = userRepo.findByName(username);

    return user.map(SecurityUser::new)
        .orElseThrow(
            () -> new UsernameNotFoundException(username + " There is no such user in REPO"));
  }
}
