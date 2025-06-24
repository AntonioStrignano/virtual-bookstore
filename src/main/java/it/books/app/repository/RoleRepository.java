package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
