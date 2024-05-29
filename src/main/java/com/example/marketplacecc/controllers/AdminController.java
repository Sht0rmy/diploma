package com.example.marketplacecc.controllers;

import com.example.marketplacecc.models.Image;
import com.example.marketplacecc.models.Product;
import com.example.marketplacecc.models.ProductType;
import com.example.marketplacecc.services.ImageService;
import com.example.marketplacecc.services.ProductService;
import com.example.marketplacecc.services.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;



@Controller
@RequiredArgsConstructor
public class AdminController {
    private final ProductService productService;
    private final ProductTypeService productTypeService;
    private final ImageService imageService;

    @GetMapping("/admin-panel")
    public String adminka(){
        return "adminka";
    }

    @GetMapping("/create")
    public String createProductTemplate(Model model){
        model.addAttribute("allTypes", productTypeService.getAll());
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                @RequestParam String type,
                                Product product) throws IOException {
        System.out.println(type);
        ProductType productTypeFromDb = productTypeService.getByType(type);
        if(productTypeFromDb != null)
            product.setProductType(productTypeFromDb);

        product = productService.save(product);

        if(file1 != null){
            product.setPreviewImage(imageService.save(file1));
        }
        if(file2 != null && file2.getSize() != 0) {
            imageService.save(file2, product);
        }
        if(file3 != null && file3.getSize() != 0) {
            imageService.save(file3, product);
        }

        return ("redirect:/");
    }
}
