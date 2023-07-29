package com.insightsystem.productservice;

import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insightsystem.productservice.dto.ProductRequest;
import com.insightsystem.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Slf4j
class ProductServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.8");

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		log.info("Setting properties {}", mongoDBContainer.getReplicaSetUrl());
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
	}



	@Test
	void shouldCreateProduct() throws Exception
	{
		ProductRequest request = getProductRequest();
		String productRequestStr = objectMapper.writeValueAsString(request);
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
															 .contentType(MediaType.APPLICATION_JSON)
															 .content(productRequestStr))
							  .andExpect(status().isCreated()).andReturn();

		Assertions.assertEquals(productRepository.findAll().size(), 1);
		Assertions.assertEquals(r.getResponse().getStatus(), HttpStatus.CREATED.value());
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
							 .name("Iphone 12")
							 .description("Iphone 12")
							 .price(BigDecimal.valueOf(78211))
							 .build();
	}

}
