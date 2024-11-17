package com.duc.manager.repository;

import com.duc.manager.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customers,Integer> {
}
