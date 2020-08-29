package com.ar.gl.feign.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

	private Long id;

	private String name;

	private String description;

	private Double price;

	private Stock stock;

	private Boolean enabled;

	private Category category;

}