package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
