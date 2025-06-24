package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.ShopAssistant;

public interface ShopAssistantRepository extends JpaRepository<ShopAssistant, Integer> {

}
