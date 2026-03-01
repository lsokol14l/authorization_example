package by.michael.authorization_example.datasource.mapper;

import by.michael.authorization_example.datasource.entity.UserEntity;
import by.michael.authorization_example.domain.model.Role;
import by.michael.authorization_example.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User toDomain(UserEntity entity) {
    Role role = Role.fromString(entity.getRole());
    return new User(entity.getName(), entity.getPassword(), role);
  }

  public UserEntity toEntity(User user) {
    UserEntity entity = new UserEntity();
    entity.setName(user.getUsername());
    entity.setPassword(user.getPassword());
    entity.setRole(user.getRole().getAuthority());
    return entity;
  }
}
