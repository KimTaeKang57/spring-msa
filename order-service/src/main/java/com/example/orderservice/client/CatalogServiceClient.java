package com.example.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "catalog-service")
public interface CatalogServiceClient {

    @PostMapping("/catalog-service/catalogs/{productId}/{qty}")
    void buyCatalog(@PathVariable("productId") String productId, @PathVariable("qty") Integer qty);
}
