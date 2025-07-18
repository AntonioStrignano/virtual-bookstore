package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

}
