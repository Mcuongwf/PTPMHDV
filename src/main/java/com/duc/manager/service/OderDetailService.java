package com.duc.manager.service;

import com.duc.manager.entity.OrderDetails;
import com.duc.manager.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OderDetailService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;


    public List<OrderDetails> getOrderDetails(){
        return orderDetailsRepository.findAll();
    }

    public OrderDetails getOrder(int Id){
        return orderDetailsRepository.findById(Id).get();
    }
}
