package com.scaler.productmicroservice;

import com.scaler.productmicroservice.projections.ProductWithTitleAndDescription;
import com.scaler.productmicroservice.repositories.CategoryRepository;
import com.scaler.productmicroservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AllArgsConstructor
class ProductMicroserviceApplicationTests {

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	public void testTC() {
		ProductWithTitleAndDescription product = productRepository.getTitleAndDescription(2L);
	}
}
