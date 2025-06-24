package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
