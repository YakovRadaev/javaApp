package com.example.springSecurityApplication.controllers;

import com.example.springSecurityApplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public String getAllProduct(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "/product/product";
    }

    @GetMapping("/product/info/{id}")
    public String infoUser(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProductId(id));
        return "product/infoProduct";
    }

}
