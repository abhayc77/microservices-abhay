package com.insightsystem.orderservice.dto;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest
{
	private List<OrderLineItemDto> orderLineItemsDtoList;
}
