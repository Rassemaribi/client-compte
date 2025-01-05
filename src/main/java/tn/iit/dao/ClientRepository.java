package tn.iit.dao;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.iit.entity.Client;
import tn.iit.entity.Compte;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    @Query("SELECT c.firstName FROM Client c WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<String> findLastNamesByTerm(@Param("term") String term);

    Client findByCin(String cin);
    @Query("SELECT c.cin FROM Client c WHERE c.cin LIKE :term")
    List<String> findCinByTerm(@Param("term") String term);
}
