package com.supun.productapi.product;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;

@Tag(name = "Product API", description = "API for managing products")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        logger.info("Fetching all products");
        List<ProductDto> products = productService.getAllProducts();
        return  ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct( @Valid @RequestBody ProductDto productDto) {
        logger.info("Adding new product: {}", productDto.getName());
        ProductDto createdProduct = productService.addProduct(productDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProduct.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        logger.info("Fetching product by id: {}", id);
        ProductDto productOpt = productService.getProductById(id);
        return ResponseEntity.ok(productOpt);
    }

    @Operation(summary = "Update a product", description = "Update an existing product by its ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct( @PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        logger.info("Updating product id: {}", id);
        ProductDto updatedProduct = productService.updateProduct(id, productDto);
        return  ResponseEntity.ok(updatedProduct);
    }

    @Operation(summary = "Delete a product", description = "Delete a product by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        logger.info("Deleting product id: {}", id);
      productService.deleteProduct(id);
      return ResponseEntity.noContent().build();

    }

}
