package com.example.marketplacecc.services;

import com.example.marketplacecc.models.ProductType;
import com.example.marketplacecc.repositories.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@Slf4j
@RequiredArgsConstructor
public class ProductTypeService {
    private final ProductTypeRepository productTypeRepository;

    public ProductType save(ProductType productType){
        return productTypeRepository.save(productType);
    }

    public List<ProductType> getAll(){
        return productTypeRepository.findAll();
    }

    public void delete(ProductType productType){
        productTypeRepository.delete(productType);
    }

    public ProductType getByType(String type){
        return productTypeRepository.findByType(type);
    }

    public ProductType getById(Long id){
        return productTypeRepository.findById(id).orElse(null);
    }
}
