package com.scaler.productmicroservice.repositories;

import com.scaler.productmicroservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);
}
