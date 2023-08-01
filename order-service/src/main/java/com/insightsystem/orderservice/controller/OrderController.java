package com.insightsystem.orderservice.controller;

import com.insightsystem.orderservice.dto.OrderRequest;
import com.insightsystem.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController
{
	private final OrderService orderService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
	public String placeOrder(@RequestBody OrderRequest orderRequest)
	{
		orderService.placeOrder(orderRequest);
		return "Order placed successfully";
	}

	public String fallbackMethod(@RequestBody OrderRequest orderRequest, RuntimeException runtimeException){
		return "Something went wrong, place order after some time";
	}

}
