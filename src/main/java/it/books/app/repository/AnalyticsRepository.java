package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Analytic;

public interface AnalyticsRepository extends JpaRepository<Analytic, Integer> {

}
