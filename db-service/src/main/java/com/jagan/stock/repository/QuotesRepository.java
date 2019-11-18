package com.jagan.stock.repository;

import com.jagan.stock.model.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuotesRepository extends MongoRepository<Quote,Integer> {
    List<Quote> findByUserName(String userName);
}
