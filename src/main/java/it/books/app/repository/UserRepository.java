package it.books.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findById(Integer id);

}
