package com.insightsystem.orderservice.service;

import java.util.List;
import java.util.UUID;

import com.insightsystem.orderservice.dto.OrderLineItemDto;
import com.insightsystem.orderservice.dto.OrderRequest;
import com.insightsystem.orderservice.model.Order;
import com.insightsystem.orderservice.model.OrderLineItem;
import com.insightsystem.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService
{
	private final OrderRepository orderRepository;

	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemsDtoList()
										  .stream()
										  .map(this::mapToDto)
										  .toList();
		order.setOrderLineItems(orderLineItems);
		orderRepository.save(order);
	}

	private OrderLineItem mapToDto(final OrderLineItemDto orderLineItemsDto)
	{
		final OrderLineItem orderLineItem = new OrderLineItem();
		orderLineItem.setSkucode(orderLineItemsDto.getSkucode());
		orderLineItem.setPrice(orderLineItemsDto.getPrice());
		orderLineItem.setQuantity(orderLineItemsDto.getQuantity());
		return orderLineItem;
	}
}
