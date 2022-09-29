package com.example.catalogservice.controller;

import com.example.catalogservice.dto.CatalogResponse;
import com.example.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/catalog-service")
@RestController
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("/catalogs")
    public ResponseEntity<?> getAllCatalog(){
        List<CatalogResponse> list = catalogService.getAll();
        return ResponseEntity.ok(list);
    }
}
