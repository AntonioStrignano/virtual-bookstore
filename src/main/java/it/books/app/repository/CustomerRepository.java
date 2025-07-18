package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
