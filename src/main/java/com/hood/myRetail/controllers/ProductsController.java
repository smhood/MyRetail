package com.hood.myRetail.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hood.myRetail.interfaces.IProductService;
import com.hood.myRetail.models.Product;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
	@Autowired
	private IProductService productService;
	
	@GetMapping("/{id}")
	public Product get(@PathVariable("id") long id) {
		return this.productService.getProduct(id);
	}
	
	@PutMapping("/{id}")
	public Product get(@PathVariable("id") long id, @RequestBody Product product) {
		return product;
	}
}
