package com.espelimbergo.lab_7;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private List<Product> prods = new ArrayList<>();
    private long nextId = 4; // start after initial mock products

    // Initialize with 3 mock products
    public ProductController() {
        prods.add(new Product(1L, "Laptop Pro", 1200.0));
        prods.add(new Product(2L, "Smartphone X", 800.0));
        prods.add(new Product(3L, "Tablet Z", 500.0));
    }

    // GET all products
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(prods);
    }

    // GET single product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        for (Product p : prods) {
            if (p.getId().equals(productId)) {
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // POST create new product
    @PostMapping("/newProduct")
    public ResponseEntity<Product> newProduct(@RequestBody Product product) {
        product.setId(nextId++);
        prods.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // PUT update existing product
    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        for (int i = 0; i < prods.size(); i++) {
            if (prods.get(i).getId().equals(productId)) {
                product.setId(productId); // preserve ID
                prods.set(i, product);
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // DELETE product
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        for (int i = 0; i < prods.size(); i++) {
            if (prods.get(i).getId().equals(productId)) {
                prods.remove(i);
                return ResponseEntity.ok().build(); // 200 OK
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 if not found
    }
}
