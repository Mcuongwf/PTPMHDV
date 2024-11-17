package com.duc.manager.controller;

import com.duc.manager.dto.request.OrderCreationRequest;
import com.duc.manager.dto.request.OrderUpdateRequest;
import com.duc.manager.dto.request.ProductUpdateRequest;
import com.duc.manager.entity.Orders;

import com.duc.manager.entity.Products;
import com.duc.manager.service.OrderService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    Orders createOrder(@RequestBody OrderCreationRequest request){
        return orderService.createOrder(request);
    }

    @GetMapping("/getOrders")
    List<Orders> getOrders(){
        return orderService.getOrders();
    }

    @GetMapping("getOrder/{Id}")
    Orders getCustomer(@PathVariable("Id") int Id){
        return orderService.getOrder(Id);
    }

    @GetMapping("getRevenue")
    double getRevenue(){
        return orderService.getRevenue();
    }
    @PutMapping("/updateOrder/{Id}")
    Orders updateOrder(@RequestBody OrderUpdateRequest request, @PathVariable int Id){
        return orderService.updateOrders(Id,request);
    }
    @GetMapping("/total_revenue")
    public ResponseEntity<Map<String, Double>> getTotalRevenue() {
        double totalRevenue = orderService.getRevenue();
        Map<String, Double> response = new HashMap<>();
        response.put("totalRevenue", totalRevenue);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getNumberOrderInMonth")
    public Map<String, Object> getNumberOrderInMonth(){
        return orderService.getNumberOrderInMonth();
    }
    @DeleteMapping("deleteOrder/{Id}")
    void deleteOrder(@PathVariable int Id){
        orderService.deleteOrder(Id);
    }





}
