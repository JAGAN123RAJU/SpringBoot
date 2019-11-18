package com.jagan.stock.client;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "db-service" , url = "http://localhost:8301")
public interface DBServiceClient {
    @GetMapping("/rest/db/{username}")
    public List<String> getQuotes(@PathVariable("username") String username);
}
