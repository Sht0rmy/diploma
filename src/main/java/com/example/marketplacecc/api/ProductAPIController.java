package com.example.marketplacecc.api;

import com.example.marketplacecc.dto.ConverterDTO;
import com.example.marketplacecc.dto.ProductDTO;
import com.example.marketplacecc.models.Product;
import com.example.marketplacecc.models.ProductType;
import com.example.marketplacecc.services.ProductService;
import com.example.marketplacecc.services.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductAPIController {
    private final ProductService productService;
    private final ProductTypeService productTypeService;

    @GetMapping("/get-all")
    public List<ProductDTO> getAllProducts(){
        return ConverterDTO.productListToDTO(productService.getAll());
    }

    @GetMapping("/get-by-type")
    public List<ProductDTO> getProductByType(@RequestParam Long id){
        List<ProductDTO> productDTOs = new ArrayList<>();
        ProductType type = productTypeService.getById(id);

        if(type != null){
            productDTOs = ConverterDTO.productListToDTO(productService.getByProductType(type));
        }
        return productDTOs;
    }

    @GetMapping("/get-popular")
    public List<ProductDTO> getPopularProducts(){
        return ConverterDTO.productListToDTO(productService.getSixProducts());
    }

    @GetMapping("/get-top-products")
    public List<ProductDTO> getTopThreeProducts(){
        return ConverterDTO.productListToDTO(productService.getThreeProducts());
    }

    @GetMapping("/get-product")
    public ProductDTO getProduct(@RequestParam Long id){
        System.out.println(id);
        Product product = productService.getById(id);
        ProductDTO productDTO = new ProductDTO();
        if(product != null){
            productDTO = ConverterDTO.productToDTO(product);
            System.out.println(productDTO);
        }
        return productDTO;
    }

    @PostMapping("/search")
    public List<ProductDTO> search(@RequestBody String word){
        List<Product> products = productService.search(word);
        List<ProductDTO> response = new ArrayList<>();
        if(!products.isEmpty()){
            response = ConverterDTO.productListToDTO(products);
        }

        return response;
    }
 }
