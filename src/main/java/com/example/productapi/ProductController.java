package com.example.productapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public ResponseEntity<List<Product>> getProducts() {
    return ResponseEntity.ok(productService.getProducts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable int id) {
    Product product = productService.getProductById(id);

    if (product == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
    }

    return ResponseEntity.ok(product);
  }

  @PostMapping("/add")
  public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
    Product created = productService.create(product);

    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

}
