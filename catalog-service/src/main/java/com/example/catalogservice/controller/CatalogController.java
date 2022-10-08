package com.example.catalogservice.controller;

import com.example.catalogservice.dto.CatalogResponse;
import com.example.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/catalog-service")
@RestController
public class CatalogController {
    private final Environment env;
    private final CatalogService catalogService;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Catalog Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", gateway ip=" + env.getProperty("gateway.ip")
                + ", message=" + env.getProperty("greeting.message")
                + ", token secret=" + env.getProperty("token.secret")
                + ", token expiration time=" + env.getProperty("token.expiration_time"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<?> getAllCatalog() {
        List<CatalogResponse> list = catalogService.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/catalogs/{productId}/{qty}")
    public void buyCatalog(@PathVariable("productId") String productId,
                           @PathVariable("qty") Integer qty) {
        catalogService.minusCatalog(productId, qty);
    }
}
