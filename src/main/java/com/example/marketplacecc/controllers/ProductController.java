package com.example.marketplacecc.controllers;

import com.example.marketplacecc.dto.ConverterDTO;
import com.example.marketplacecc.models.Product;
import com.example.marketplacecc.services.ProductService;
import com.example.marketplacecc.services.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductTypeService productTypeService;
    private final ProductService productService;

    @GetMapping("/category/{id}")
    public String getCategoryProducts(@PathVariable Long id){
        return "category";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Long id, Model model){
        Product product = productService.getById(id);
        if(product != null) {
            product.setViews(product.getViews() + 1);
            productService.save(product);

            model.addAttribute("product", ConverterDTO.productToDTO(product));
            return "product";
        }else
            return "index";
    }

    @GetMapping("/basket")
    public String getBasket(){
        return "basket";
    }

    @GetMapping("/wishlist")
    public String getWish(){
        return "wishlist";
    }

    @GetMapping("/advanced-search")
    public String advancedSearch(){
        return "advancedSearch";
    }
}
