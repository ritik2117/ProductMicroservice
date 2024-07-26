package com.scaler.productmicroservice.repositories;

import com.scaler.productmicroservice.models.Product;
import com.scaler.productmicroservice.projections.ProductWithTitleAndDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);

//    HQL
    @Query("select p.title, p.description from Product p where p.id = :id")
    ProductWithTitleAndDescription getTitleAndDescription(@Param("id") Long id);
}
