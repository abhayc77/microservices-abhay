package com.insightsystem.orderservice.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_order_line_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItem
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_line_item_seq")
	@SequenceGenerator(name="order_line_item_seq", sequenceName = "order_line_item_sequence", allocationSize = 1)
	private Long id;

	private String skucode;
	private BigDecimal price;
	private Integer quantity;
}
