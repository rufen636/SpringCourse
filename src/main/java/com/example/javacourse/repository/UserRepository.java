package com.example.javacourse.repository;

import com.example.javacourse.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLogin(String login);
    User findByLoginAndPassword(String login, String password);

    Optional<User> findByLogin(String login);

}