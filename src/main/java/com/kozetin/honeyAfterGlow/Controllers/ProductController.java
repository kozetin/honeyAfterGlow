package com.kozetin.honeyAfterGlow.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class ProductController {
    
}
