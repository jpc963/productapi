package com.example.productapi;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final Map<Integer, Product> products = new ConcurrentHashMap<>();

  public ProductService() {
    Product productSeed = new Product();
    productSeed.setId(195);
    productSeed.setTitle("Hyaluronic Acid Serum");
    productSeed.setPrice(19);
    productSeed.setDiscountPercentage(13.31);
    productSeed.setStock(110);
    productSeed.setRating(4.83);
    productSeed.setImages(List.of(
        "https://i.dummyjson.com/data/products/16/1.png",
        "https://i.dummyjson.com/data/products/16/2.webp",
        "https://i.dummyjson.com/data/products/16/3.jpg",
        "https://i.dummyjson.com/data/products/16/4.jpg",
        "https://i.dummyjson.com/data/products/16/thumbnail.jpg"));
    productSeed.setThumbnail("https://i.dummyjson.com/data/products/16/thumbnail.jpg");
    productSeed.setDescription(
        "L'Oréal Paris introduces Hyaluron Expert Replumping Serum formulated with 1.5% Hyaluronic Acid");
    productSeed.setBrand("L'Oreal Paris");
    productSeed.setCategory("skincare");
    products.put(productSeed.getId(), productSeed);
  }

  public List<Product> getProducts() {
    return List.copyOf(products.values());
  }

  public Product getProductById(int id) {
    return products.get(id);
  }

  public Product create(Product product) {
    products.put(product.getId(), product);
    return product;
  }
}