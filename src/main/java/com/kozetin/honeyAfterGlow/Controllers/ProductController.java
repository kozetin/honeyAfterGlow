package com.kozetin.honeyAfterGlow.Controllers;

import com.kozetin.honeyAfterGlow.Domain.Product;
import com.kozetin.honeyAfterGlow.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/addproduct")
    public String addProduct() {
        return "addproduct";
    }

    @PostMapping("/addproduct")
    public String addProduct(Product product, Model model, @RequestParam("file") MultipartFile file) {
        String addProductMessage = productService.addProduct(product, file);
        if (!addProductMessage.equals("OK")) {
            model.addAttribute("message",addProductMessage);
            return "addproduct";
        }

        return "redirect:/";
    }
}
