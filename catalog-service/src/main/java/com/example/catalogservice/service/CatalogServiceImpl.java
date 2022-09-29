package com.example.catalogservice.service;

import com.example.catalogservice.dao.Catalog;
import com.example.catalogservice.dto.CatalogResponse;
import com.example.catalogservice.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    @Override
    public List<CatalogResponse> getAll() {
        List<Catalog> allCatalog = catalogRepository.findAll();
        List<CatalogResponse> catalogs = new ArrayList<>();
        for (Catalog catalog : allCatalog) {
            catalogs.add(CatalogResponse.builder()
                    .productId(catalog.getProductId())
                    .productName(catalog.getProductName())
                    .unitPrice(catalog.getUnitPrice())
                    .build());
        }
        return catalogs;
    }
}
