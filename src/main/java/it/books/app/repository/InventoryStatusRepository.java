package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.InventoryStatus;

public interface InventoryStatusRepository extends JpaRepository<InventoryStatus, Integer> {

}
