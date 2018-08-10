package com.hood.myRetail.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hood.myRetail.interfaces.IProductService;
import com.hood.myRetail.models.Price;
import com.hood.myRetail.models.Product;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
	@Autowired
	private IProductService productService;
	
	@GetMapping("/")
	public List<Price> list() {
		return this.productService.getAvailablePrices();
	}
	
	@GetMapping("/{id}")
	public Product get(@PathVariable("id") long id) {
		return this.productService.getProduct(id);
	}
	
	@PutMapping("/{id}")
	public Boolean put(@PathVariable("id") long id, @RequestBody Price price) {
		return this.productService.addPrice(id, price);
	}
}
