package com.duc.manager.controller;

import com.duc.manager.dto.request.ProductCreationRequest;
import com.duc.manager.dto.request.ProductUpdateRequest;
import com.duc.manager.entity.Products;
import com.duc.manager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/createProduct")
    Products createProduct(@RequestBody ProductCreationRequest request){
        return productService.createProduct(request);
    }

    @GetMapping("/getProducts")
    List<Products> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("getProduct/{Id}")
    Products getProduct(@PathVariable("Id") int Id){
        return productService.getProductById(Id);
    }

    @PutMapping("/updateProduct/{Id}")
    Products updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable int Id){
        return productService.updateProducts(Id,request);
    }

    @GetMapping("getTop5")
    public List<Map<String, Object>> getTopProductSales()
    { return productService.getTop5(); }


    @GetMapping("getQuantity")
    public ResponseEntity<Map<String, Long>> getTotalRevenue() {
        Long quantity = productService.getQuantityProduct();
        Map<String, Long> response = new HashMap<>();
        response.put("totalQuantity", quantity);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("deleteProduct/{Id}")
    void deleteProduct(@PathVariable int Id){
        productService.deleteProduct(Id);
    }

    @GetMapping("getProductInMonth")
    public ResponseEntity<Map<String,Integer>> getProductInMonth(){
        int number = productService.getProductInMonth();
        Map<String,Integer> response = new HashMap<>();
        response.put("numberOfProducts",number);
        return  ResponseEntity.ok(response);
    }

}
