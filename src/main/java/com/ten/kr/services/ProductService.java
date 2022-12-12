package com.ten.kr.services;

import com.ten.kr.entity.Product;
import com.ten.kr.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public boolean saveProduct(Product product){
        productRepository.save(product);
        return true;
    }
}
