package by.michael.authorization_example.datasource.repository;

import by.michael.authorization_example.datasource.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
  Optional<UserEntity> findByLogin(String login);

  boolean existsByLogin(String login);
}
