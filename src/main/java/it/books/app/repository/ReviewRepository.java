package it.books.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    public List<Review> findByCustomerId(Integer id);

}
