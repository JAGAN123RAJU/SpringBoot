package com.jagan.stock.controller;

import com.jagan.stock.model.Quote;
import com.jagan.stock.repository.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DBServiceController {

    @Autowired
    private QuotesRepository quotesRepository;

    @GetMapping("/")
    public String greet() {
        return "welcome";

    }
    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") String username) {
        return quotesRepository.findByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList())
                ;

    }

    @PostMapping("/add")
    public void add(@RequestBody Quote quote){
        quotesRepository.insert(quote);
    }

    @DeleteMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") String username){
        quotesRepository.findByUserName(username)
                .forEach((quote) -> quotesRepository.delete(quote));
        return getQuotes(username);
    }
}
