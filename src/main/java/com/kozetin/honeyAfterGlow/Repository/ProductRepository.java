package com.kozetin.honeyAfterGlow.Repository;

import com.kozetin.honeyAfterGlow.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByType(String type);
}
