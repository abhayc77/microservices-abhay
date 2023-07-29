package com.insightsystem.productservice.controller;

import java.util.List;

import com.insightsystem.productservice.dto.ProductRequest;
import com.insightsystem.productservice.dto.ProductResponse;
import com.insightsystem.productservice.service.ProductService;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController
{
	private final ProductService productService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest){
		productService.createProduct(productRequest);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProducts() {
		return productService.getAllProducts();
	}


}
