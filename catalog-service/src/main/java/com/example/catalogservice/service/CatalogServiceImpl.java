package com.example.catalogservice.service;

import com.example.catalogservice.dao.Catalog;
import com.example.catalogservice.dto.CatalogRequest;
import com.example.catalogservice.dto.CatalogResponse;
import com.example.catalogservice.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
//                    .productName(catalog.getProductName())
                    .unitPrice(catalog.getUnitPrice())
                    .build());
        }
        return catalogs;
    }

    @Transactional
    @Override
    public void minusCatalog(String productId, Integer qty) {
        Catalog catalog = catalogRepository.findByProductId(productId);
        catalog.setStock(qty);
    }

    @Override
    public CatalogResponse createCatalog(CatalogRequest catalogRequest) {
        Catalog catalog = catalogRepository.save(Catalog.builder()
                .productId(catalogRequest.getProductId())
                .stock(catalogRequest.getQty())
                .unitPrice(catalogRequest.getUnitPrice())
                .build());

        return CatalogResponse.builder()
                .productId(catalog.getProductId())
                .qty(catalog.getStock())
                .unitPrice(catalog.getUnitPrice())
                .totalPrice(catalog.getUnitPrice() * catalog.getStock())
                .build();
    }
}
