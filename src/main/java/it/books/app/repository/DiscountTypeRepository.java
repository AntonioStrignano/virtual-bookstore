package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.DiscountType;

public interface DiscountTypeRepository extends JpaRepository<DiscountType, Integer> {

}
