package com.insightsystem.productservice.model;


import java.math.BigDecimal;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Product
{
	@Id
	private String id;
	private String name;
	private String description;
	private BigDecimal price;


}
