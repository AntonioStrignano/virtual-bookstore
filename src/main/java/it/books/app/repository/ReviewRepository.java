package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
