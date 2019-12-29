package com.kozetin.honeyAfterGlow.Service;

import com.kozetin.honeyAfterGlow.Domain.Product;
import com.kozetin.honeyAfterGlow.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public String addProduct(Product product, MultipartFile file) {

        //product name validation
        if (product.getName().isEmpty() || product.getName() == null) {
            return "Введите значение в поле Название товара";
        }

        //productDescription validation
        if (product.getDescription().isEmpty() || product.getDescription() == null) {
            return "Введите значение в поле Описание товара";
        }

        //productPriceValidation
        if (product.getPrice() == null || product.getPrice() == 0) {
            return "Введите значение в поле цена, отличное от 0";
        }

        if (file != null && !file.isEmpty()) {
            File uploadFolder = new File(uploadPath);

            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile +"."+file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPath + "/" + resultFileName));
            } catch (IOException ex) {
                return "Что-то не так с файлом";
            }
            product.setImageSource(resultFileName);
        } else {
            return "Файл не выбран";
        }


        productRepository.save(product);
        return "OK";
    }
}
