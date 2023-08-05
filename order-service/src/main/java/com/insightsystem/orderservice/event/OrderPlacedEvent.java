package com.insightsystem.orderservice.event;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacedEvent
{
	private String orderNumber;
}
