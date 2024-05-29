package com.example.marketplacecc.services;

import com.example.marketplacecc.models.Image;
import com.example.marketplacecc.models.Product;
import com.example.marketplacecc.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;



@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image save(MultipartFile file) throws IOException {
        return imageRepository.save(toImageEntity(file));
    }

    public Image save(MultipartFile file, Product product) throws IOException {
        return imageRepository.save(toImageEntity(file, product));
    }
    public void update(Image image){
        imageRepository.save(image);
    }

    public Image getById(Long id){
        return imageRepository.findById(id).orElse(null);
    }
    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    private Image toImageEntity(MultipartFile file, Product product) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        image.setProduct(product);
        return image;
    }

    public void remove(Image image){
        imageRepository.delete(image);
    }
}
