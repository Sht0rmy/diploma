package com.example.marketplacecc.api;

import com.example.marketplacecc.models.ProductType;
import com.example.marketplacecc.services.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductTypeAPIController {
    private final ProductTypeService productTypeService;

    @PostMapping("/create-product-type")
    public HttpStatus createProductType(@RequestParam String type){
        if(!type.isEmpty()){
            ProductType productType = new ProductType();
            productType.setType(type);

            productTypeService.save(productType);
        }
        return HttpStatus.BAD_REQUEST;
    }

    @GetMapping("/get-categories")
    public List<ProductType> getCategories(){
        return productTypeService.getAll();
    }
}
