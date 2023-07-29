package com.insightsystem.orderservice.dto;

import java.math.BigDecimal;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemDto
{
	private Long id;
	private String skucode;
	private BigDecimal price;
	private Integer quantity;
}
