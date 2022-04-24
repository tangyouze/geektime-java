package com.tangyouze.week08.controller;

import com.tangyouze.week08.dao.ProductOrder;
import com.tangyouze.week08.dao.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    @ResponseBody
    public List<ProductOrder> findAll(){
        return productRepository.findAll();
    }
}
