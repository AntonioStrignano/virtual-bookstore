package it.books.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    public List<Order> findByCustomerId(Integer id);

}
