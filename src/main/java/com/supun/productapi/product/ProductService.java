package com.supun.productapi.product;


import com.supun.productapi.errorhandler.ProductNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
}

public List<ProductDto> getAllProducts() {
    return productRepository.findAll()
            .stream()
            .map(product -> new ProductDto(product.getId(), product.getName(), product.getPrice()))
            .collect(Collectors.toList());
}
public ProductDto addProduct(ProductDto productDto) {

    Product product = new Product();
    product.setId(null);
    product.setName(productDto.getName());
    product.setPrice(productDto.getPrice());
    Product savedProduct = productRepository.save(product);
    return new ProductDto(savedProduct.getId(), savedProduct.getName(), savedProduct.getPrice());
}
    public ProductDto getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> new ProductDto(product.getId(), product.getName(), product.getPrice()))
                .orElseThrow(() -> new ProductNotFound("Product not found with id: " + id));
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {

        return productRepository.findById(id).map(product -> {
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            Product updatedProduct = productRepository.save(product);
            return new ProductDto(updatedProduct.getId(), updatedProduct.getName(), updatedProduct.getPrice());
        }).orElseThrow(() -> new ProductNotFound("Product not found with id: " + id));
    }

    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFound("Product not found with id: " + id);
        }
    }

}
