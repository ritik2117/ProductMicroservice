package com.scaler.productmicroservice;

import com.scaler.productmicroservice.projections.ProductWithTitleAndDescription;
import com.scaler.productmicroservice.repositories.CategoryRepository;
import com.scaler.productmicroservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductMicroserviceApplicationTests {

    @Autowired
	private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	public void testTC() {
		ProductWithTitleAndDescription product = productRepository.getTitleAndDescription(2L);
        System.out.println(product.getTitle());
        System.out.println(product.getDescription());
		System.out.println(product.getCreatedAt());

        ProductWithTitleAndDescription productSQL = productRepository.getTitleAndDescriptionWithSQL(2L);
        System.out.println(productSQL.getTitle());
        System.out.println(productSQL.getDescription());
	}
}
