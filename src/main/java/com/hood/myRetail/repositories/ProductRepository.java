package com.hood.myRetail.repositories;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import com.hood.myRetail.interfaces.IProductRepository;

@Repository
public class ProductRepository implements IProductRepository{
	
	private RestTemplate restTemplate;
	private String targetUri;
	private String targetExcludes;
	
	public ProductRepository() {
		this.targetUri = "https://redsky.target.com/v2/pdp/tcin/";
		this.targetExcludes = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
		this.restTemplate = new RestTemplate();
	}
	
	public String getProductInfo(long id) {
		String uri = this.targetUri + id + this.targetExcludes;
		
		try {
			ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);
			return response.getBody();
		}
		catch(Exception ex) {
			
		}
		
		return "";
	}
}

