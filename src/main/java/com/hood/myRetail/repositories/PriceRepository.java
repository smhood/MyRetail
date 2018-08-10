package com.hood.myRetail.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.hood.myRetail.models.Price;

@Repository
public interface PriceRepository extends MongoRepository<Price, Long>{
	
}
