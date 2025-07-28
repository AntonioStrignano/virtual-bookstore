package it.books.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    List<Inventory> findByBookId(Integer bookId);

}
