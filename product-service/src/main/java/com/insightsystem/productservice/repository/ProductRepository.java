package com.insightsystem.productservice.repository;

import com.insightsystem.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>
{
}
