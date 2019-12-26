package com.kozetin.honeyAfterGlow.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class ProductController {

    @GetMapping("/addproduct")
    public String addProduct() {
        return "addproduct";
    }
}
