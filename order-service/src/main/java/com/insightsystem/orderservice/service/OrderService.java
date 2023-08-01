package com.insightsystem.orderservice.service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import com.insightsystem.orderservice.dto.*;
import com.insightsystem.orderservice.model.Order;
import com.insightsystem.orderservice.model.OrderLineItem;
import com.insightsystem.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService
{
	private final OrderRepository orderRepository;
	private final WebClient.Builder inventoryServiceWebClientBuilder;

	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemsDtoList()
										  .stream()
										  .map(this::mapToDto)
										  .toList();
		order.setOrderLineItems(orderLineItems);
		List<String> skuCodeList = orderLineItems.stream()
												 .map(OrderLineItem::getSkucode)
												 .toList();
		InventoryResponse[] inventoryResponseArray = inventoryServiceWebClientBuilder.build().get()
															.uri("http://InventoryService/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCodes",skuCodeList).build())
															.retrieve()
															.bodyToMono(InventoryResponse[].class)
															.block();
												// check bodyToMono how it works
		boolean allProductsInStock;
		if (inventoryResponseArray != null)
		{
			 allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
		} else {
			allProductsInStock = false;
		}
		if (allProductsInStock)
		{
			orderRepository.save(order);
			return "Order Placed Successfully";
		} else {
			throw new IllegalArgumentException("Product is not in stock, please try again later");
		}


	}

	private OrderLineItem mapToDto(final OrderLineItemDto orderLineItemsDto)
	{
		final OrderLineItem orderLineItem = new OrderLineItem();
		orderLineItem.setSkucode(orderLineItemsDto.getSkuCode());
		orderLineItem.setPrice(orderLineItemsDto.getPrice());
		orderLineItem.setQuantity(orderLineItemsDto.getQuantity());
		return orderLineItem;
	}
}
