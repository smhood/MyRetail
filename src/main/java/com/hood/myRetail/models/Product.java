package com.hood.myRetail.models;

public class Product {
	private Long id;
	private String name;
	private Price current_price;
	
	public Product(Long id, String name, Price current_price) {
		this.id = id;
		this.name = name;
		this.current_price = current_price;
	}
	
	public Product() {
		
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Price getCurrent_price() {
		return current_price;
	}
	public void setCurrent_price(Price current_price) {
		this.current_price = current_price;
	}
}
