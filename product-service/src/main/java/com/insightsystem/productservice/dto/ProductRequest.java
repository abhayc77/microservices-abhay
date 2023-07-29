package com.insightsystem.productservice.dto;

import java.math.BigDecimal;

import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductRequest
{
	private String name;
	private String description;
	private BigDecimal price;
}
