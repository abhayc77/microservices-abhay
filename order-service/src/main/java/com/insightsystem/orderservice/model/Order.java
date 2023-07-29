package com.insightsystem.orderservice.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
	@SequenceGenerator(name="order_id_seq", sequenceName = "order_id_seq", allocationSize = 1)
	private Long id;
	private String orderNumber;

	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderLineItem> orderLineItems;

}
