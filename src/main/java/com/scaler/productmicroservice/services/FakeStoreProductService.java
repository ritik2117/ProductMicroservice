package com.scaler.productmicroservice.services;

import com.scaler.productmicroservice.dtos.FakeStoreProductDto;
import com.scaler.productmicroservice.models.Category;
import com.scaler.productmicroservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

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

    private FakeStoreProductDto convertProductToFakeStoreProductDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        if (product != null) {
            if (product.getId() != null) {
                fakeStoreProductDto.setId(product.getId());
            }
            if (product.getTitle() != null) {
                fakeStoreProductDto.setTitle(product.getTitle());
            }
            if (product.getCategory() != null) {
                fakeStoreProductDto.setCategory(product.getCategory().getTitle());
            }
            if (product.getDescription() != null) {
                fakeStoreProductDto.setDescription(product.getDescription());
            }
            if (product.getImage() != null) {
                fakeStoreProductDto.setImage(product.getImage());
            }
            fakeStoreProductDto.setPrice(product.getPrice());
        }
        return fakeStoreProductDto;
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
    public List<Product> getProductsByCategory(Category category) {
        String categoryTitle = category.getTitle();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products/category/" + categoryTitle, FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(this.convertFakeStoreDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product addProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = this.convertProductToFakeStoreProductDto(product);
        FakeStoreProductDto newFakeStoreProductDto = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        Product newProduct = this.convertFakeStoreDtoToProduct(newFakeStoreProductDto);
        return newProduct;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
//        Implemented execute method instead of put method as put method did not return the object but execute method returns the object
        FakeStoreProductDto fakeStoreProductDto = this.convertProductToFakeStoreProductDto(product);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto updatedFakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreDtoToProduct(updatedFakeStoreProductDto);
    }

    @Override
    public void deleteProduct(Long id) throws RestClientException {
//        Tried to return the Product to keep the signature of the method as Product deleteProduct(), but the below method does not return anything!
//        Product product = new Product();
//        ResponseEntity<Product> responseEntity = (ResponseEntity<Product>) restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.DELETE, (RequestCallback)null, (ResponseExtractor)null);
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            product.setId(id);
//        }
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
        return;
    }
}
