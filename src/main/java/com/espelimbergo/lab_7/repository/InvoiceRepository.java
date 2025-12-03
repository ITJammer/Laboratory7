package com.espelimbergo.lab_7.repository;

import com.espelimbergo.lab_7.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    // You can add custom queries here if needed
}
