package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
