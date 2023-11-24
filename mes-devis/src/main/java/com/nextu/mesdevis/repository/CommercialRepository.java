package com.nextu.mesdevis.repository;

import com.nextu.mesdevis.entity.Commercial;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pour l'entité Commercial.
 */
public interface CommercialRepository extends JpaRepository<Commercial, Long> {
    Commercial findByRole(String role);
}