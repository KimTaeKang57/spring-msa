package com.example.catalogservice.service;

import com.example.catalogservice.dto.CatalogResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogService {

    List<CatalogResponse> getAll();
}
