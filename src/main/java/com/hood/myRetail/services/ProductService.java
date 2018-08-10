package com.hood.myRetail.services;

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
	
	public List<Price> getAvailablePrices() {
		List<Price> prices = this.priceRepository.findAll();
		
		return prices;
	}
	
	public Product getProduct(long id) {
		Product product = new Product();
		Price price = new Price();
		try {
			String productJson = this.productRepository.getProductInfo(id);
			
			if(productJson.length() < 1) {
				return product;
			}
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(productJson);
			JsonNode item = root.findPath("item");

			Optional<Price> result = this.priceRepository.findById(id);
			
			if(result.isPresent()) {
				price = result.get();
			}
			
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
			
			if(productJson.length() < 1) {
				return false;
			}
			
			price.setId(id);
			
			this.priceRepository.save(price);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return true;
	}
}
