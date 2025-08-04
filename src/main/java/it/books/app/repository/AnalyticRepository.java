package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Analytic;

public interface AnalyticRepository extends JpaRepository<Analytic, Integer> {

    public void deleteByCustomerId(Integer customerId);

}
