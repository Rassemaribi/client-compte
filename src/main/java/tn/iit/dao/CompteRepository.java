package tn.iit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.iit.entity.Compte;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

	List<Compte> findByNomClientContains(String key);

	@Query("SELECT c.nomClient FROM Compte c WHERE c.nomClient LIKE %:term%")
	List<String> findAccountNamesByTerm(@Param("term") String term);
}