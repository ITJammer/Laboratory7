package com.espelimbergo.lab_7.service;

import com.espelimbergo.lab_7.entity.Customer;
import com.espelimbergo.lab_7.entity.Invoice;
import com.espelimbergo.lab_7.entity.Product;
import com.espelimbergo.lab_7.repository.CustomerRepository;
import com.espelimbergo.lab_7.repository.InvoiceRepository;
import com.espelimbergo.lab_7.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    // Create new invoice linking customer and products
    public Invoice createInvoice(Long customerId, List<Long> productIds) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Product> products = productRepository.findAllById(productIds);

        Invoice invoice = new Invoice();
        invoice.setDate(LocalDate.now());
        invoice.setCustomer(customer);
        invoice.setProducts(products);

        return invoiceRepository.save(invoice);
    }

    // Get all invoices
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    // Get invoice by ID
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    // Delete invoice by ID
    public void deleteInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoiceRepository.delete(invoice);
    }
}
