package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {

}
