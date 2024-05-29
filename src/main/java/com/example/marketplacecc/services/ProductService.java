package com.example.marketplacecc.services;

import com.example.marketplacecc.models.Product;
import com.example.marketplacecc.models.ProductType;
import com.example.marketplacecc.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product getById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public void delete(Product product){
        productRepository.delete(product);
    }

    public List<Product> getByProductType(ProductType productType){
        return productRepository.findByProductType(productType);
    }

    public List<Product> getThreeProducts(){
        return productRepository.findTop3ByOrderByDateOfCreatedDesc();
    }


    public List<Product> getSixProducts(){
        return productRepository.findTop6ByOrderByDateOfCreatedDesc();
    }

    public List<Product> search(String word){
        return productRepository.findByTitleContaining(word);
    }
}
