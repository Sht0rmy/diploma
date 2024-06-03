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
        Product product = productService.getById(id);
        ProductDTO productDTO = new ProductDTO();
        if(product != null){
            productDTO = ConverterDTO.productToDTO(product);

            product.setViews(product.getViews() + 1);
            productService.save(product);
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

    @GetMapping("/advanced-search")
    public List<ProductDTO> advancedSearch(@RequestParam String name, @RequestParam int minPrice, @RequestParam int maxPrice,
                                           @RequestParam String category){
        List<ProductDTO> productDTOS = new ArrayList<>();

        List<Product> products = productService.getByProductType(productTypeService.getByType(category));
        if(!products.isEmpty()){
            List<Product> productToResponse = new ArrayList<>();
            for (Product product : products) {
                if(product.getTitle().contains(name) && product.getPrice() >= minPrice && product.getPrice() <= maxPrice){
                    productToResponse.add(product);
                }
            }
            if(!productToResponse.isEmpty())
                productDTOS = ConverterDTO.productListToDTO(productToResponse);
        }

        return productDTOS;
    }
 }
