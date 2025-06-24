package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.WarehouseLocation;

public interface WarehouseLocationRepository extends JpaRepository<WarehouseLocation, Integer> {

}
