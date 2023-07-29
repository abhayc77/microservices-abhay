package com.insightsystem.productservice.dto;

import java.math.BigDecimal;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponse
{
	private String id;
	private String name;
	private String description;
	private BigDecimal price;
}
