package com.duc.manager.service;

import com.duc.manager.dto.request.ProductCreationRequest;
import com.duc.manager.dto.request.ProductUpdateRequest;
import com.duc.manager.entity.Products;
import com.duc.manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public Products createProduct(ProductCreationRequest request){
        Products product= new Products();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setImgFileName(request.getImgFileName());
        product.setStock(request.getStock());
        product.setUpdateDate(LocalDate.now());
        return productRepository.save(product);

    }

    public List<Products> getProducts(){
        return productRepository.findAll();
    }

    public Products getProductById(int Id){
        return productRepository.findById(Id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public long getQuantityProduct(){
        return productRepository.count();
    }

    public Products updateProducts(int Id, ProductUpdateRequest request){
        Products product= getProductById(Id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setUpdateDate(LocalDate.now());
       return productRepository.save(product);
    }

    public List<Map<String, Object>> getTop5(){
        return productRepository.getTop5();
    }

    public void deleteProduct(int Id){
        productRepository.deleteById(Id);
    }

    public  int getProductInMonth(){
        return productRepository.getProductInMonth();
    }

}
