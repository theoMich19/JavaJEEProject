package com.nextu.mesdevis.repository;

import com.nextu.mesdevis.entity.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository pour l'entité Devis.
 */
@Repository
public interface DevisRepository extends JpaRepository<Devis, Long> {
    @Query("SELECT e FROM Devis e WHERE e.idDevis IN :ids")
    List<Devis> findByIds(@Param("ids") List<Long> ids);
}