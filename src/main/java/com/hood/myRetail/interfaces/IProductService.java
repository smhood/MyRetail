package com.hood.myRetail.interfaces;

import java.util.List;

import com.hood.myRetail.models.Price;
import com.hood.myRetail.models.Product;

public interface IProductService {
	public List<Product> getAvailablePrices();
	public Product getProduct(long id);
	public Boolean addPrice(long id, Price price);
}
