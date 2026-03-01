package by.michael.authorization_example.datasource.repository;

import by.michael.authorization_example.datasource.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByName(String username);
}
