package com.hood.myRetail.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hood.myRetail.interfaces.IProductRepository;
import com.hood.myRetail.interfaces.IProductService;
import com.hood.myRetail.models.Price;
import com.hood.myRetail.models.Product;
import com.hood.myRetail.repositories.PriceRepository;

@Service
public class ProductService implements IProductService {
	@Autowired
	private IProductRepository productRepository;
	@Autowired
	private PriceRepository priceRepository;
	
	public List<Product> getAvailablePrices() {
		List<Product> products = new ArrayList<Product>();
		List<Price> prices = this.priceRepository.findAll();
		
		for(Price price : prices) {
			try {
				Product product = new Product();
				String productJson = this.productRepository.getProductInfo(price.getId());
				
				//If string is empty, no results were found.
				if(productJson.length() < 1) {
					continue;
				}
				
				//Attempts to map json to jsonnode so we can obtain individual values.
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(productJson);
				
				//Gets the node with the information we care about.
				JsonNode item = root.findPath("item");
				
				//Set values to product
				product.setId(item.path("tcin").asLong());
				product.setName(item.path("product_description").path("title").asText());
				product.setCurrent_price(price);
				
				products.add(product);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return products;
	}
	
	public Product getProduct(long id) {
		Product product = new Product();
		Price price = new Price();
		try {
			//Fetches string representation of product from external api
			String productJson = this.productRepository.getProductInfo(id);
			
			//If string is empty, no results were found.
			if(productJson.length() < 1) {
				return product;
			}
			
			//Attempts to map json to jsonnode so we can obtain individual values.
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(productJson);
			
			//Gets the node with the information we care about.
			JsonNode item = root.findPath("item");

			//Attempts to fetch a price from the mongodb.
			Optional<Price> result = this.priceRepository.findById(id);
			
			//If price does not exist, do not assign.
			if(result.isPresent()) {
				price = result.get();
			}
			
			
			//Set values to product
			product.setId(item.path("tcin").asLong());
			product.setName(item.path("product_description").path("title").asText());
			product.setCurrent_price(price);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return product;
	}
	
	public Boolean addPrice(long id, Price price) {
		try {
			String productJson = this.productRepository.getProductInfo(id);
			
			//Checks to see if product exists.
			//If product doesn't exist, we don't need to assign a price.
			if(productJson.length() < 1) {
				return false;
			}
			
			//Sets the id on the price object and stores it.
			price.setId(id);
			
			this.priceRepository.save(price);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return true;
	}
}
