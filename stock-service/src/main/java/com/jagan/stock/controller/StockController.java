package com.jagan.stock.controller;

import com.jagan.stock.client.DBServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/stock")
public class StockController {

    @Autowired
    DBServiceClient dBServiceClient;

    private Stock getStockPrice(String quote) {
        try {
            return YahooFinance.get(quote);
        } catch (IOException e) {
            e.printStackTrace();
            Stock s =  new Stock(quote);
            StockQuote sq = new StockQuote("");
            s.setQuote(sq);
            return s;
        }
    }

    @GetMapping("/{username}")
    public List<Quote> getStock(@PathVariable("username") String username){
         return dBServiceClient.getQuotes(username)
                .stream()
                .map(quote -> {
                    Stock s= getStockPrice(quote);
                    return new Quote(quote,s.getQuote().getPrice());
                })
                .collect(Collectors.toList());
    }

    private class Quote {
        private String quote;
        private BigDecimal price;

        public String getQuote() {
            return quote;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public Quote(String quote, BigDecimal price) {
            this.quote = quote;
            this.price = price;
        }
    }
}
