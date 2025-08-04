package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.SearchHistory;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Integer> {

    public void deleteByCustomerId(Integer id);

}
