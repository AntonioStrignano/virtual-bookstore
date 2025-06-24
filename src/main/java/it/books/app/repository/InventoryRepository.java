package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}
