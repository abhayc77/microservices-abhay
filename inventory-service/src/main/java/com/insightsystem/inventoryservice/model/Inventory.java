package com.insightsystem.inventoryservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "t_inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="inventory_id_seq")
	@SequenceGenerator(name = "inventory_id_sequence", sequenceName = "inventory_id_seq", allocationSize = 1)
    private Long id;
    private String skuCode;
    private Integer quantity;
}
