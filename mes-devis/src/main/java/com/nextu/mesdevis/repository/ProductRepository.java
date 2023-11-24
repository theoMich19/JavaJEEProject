package com.nextu.mesdevis.repository;

import com.nextu.mesdevis.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité Product.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}