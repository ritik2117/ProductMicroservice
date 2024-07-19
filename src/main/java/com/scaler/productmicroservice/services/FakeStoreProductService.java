package com.scaler.productmicroservice.services;

import com.scaler.productmicroservice.dtos.FakeStoreCategoryDto;
import com.scaler.productmicroservice.dtos.FakeStoreProductDto;
import com.scaler.productmicroservice.models.Category;
import com.scaler.productmicroservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        System.out.println("Fakestore Product DTO: " + fakeStoreProductDto.toString());
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
    @Override
    public Product getProductById(Long id) {
        /**
         * Call FakeStore API here to get the Product with given id.
         * 1st param -> URL
         * 2nd param -> Response
         */
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        if (fakeStoreProductDto == null) {
            return null;
        }

//        Convert FakeStore DTO into Product object.
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
//        Array does not use generics, they are the separate data type -> so here need to use array --> Type Erasure
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(this.convertFakeStoreDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public List<Category> getAllCategories() {
        String[] fakeStoreCategories = restTemplate.getForObject("https://fakestoreapi.com/products/categories", String[].class);
        List<Category> categories = new ArrayList<>();

        for (String fakeStoreCategory : fakeStoreCategories) {
            Category category = new Category();
            category.setTitle(fakeStoreCategory);
            categories.add(category);
        }
        return categories;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
//        Implemented execute method instead of put method as put method did not return the object but execute method returns the object
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }
}
