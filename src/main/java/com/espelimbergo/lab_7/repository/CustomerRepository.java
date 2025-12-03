package com.espelimbergo.lab_7.repository;

import com.espelimbergo.lab_7.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // You can add custom queries here if needed
}
