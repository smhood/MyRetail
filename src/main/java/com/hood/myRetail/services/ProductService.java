package com.hood.myRetail.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hood.myRetail.interfaces.IProductRepository;
import com.hood.myRetail.interfaces.IProductService;
import com.hood.myRetail.models.Price;
import com.hood.myRetail.models.Product;

@Service
public class ProductService implements IProductService {
	@Autowired
	private IProductRepository productRepository;

	public Product getProduct(long id) {
		try {
			String productJson = this.productRepository.getProductInfo(id);
			
			if(productJson.length() < 1) {
				return null;
			}
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(productJson);
			JsonNode item = root.findPath("item");

			return new Product(item.path("tcin").asLong(), item.path("product_description").path("title").asText(), null);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
