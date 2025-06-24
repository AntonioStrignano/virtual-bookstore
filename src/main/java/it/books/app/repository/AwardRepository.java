package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Award;

public interface AwardRepository extends JpaRepository<Award, Integer> {

}
