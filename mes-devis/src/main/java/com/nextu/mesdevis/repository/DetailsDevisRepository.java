package com.nextu.mesdevis.repository;

import com.nextu.mesdevis.entity.DetailsDevis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité DetailsDevis.
 */
@Repository
public interface DetailsDevisRepository extends JpaRepository<DetailsDevis, Long> {

}
