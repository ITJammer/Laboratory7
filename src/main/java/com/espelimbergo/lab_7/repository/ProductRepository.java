package com.espelimbergo.lab_7.repository;

import com.espelimbergo.lab_7.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // No methods needed, findAll(), findById(), save(), deleteById() are inherited
}
