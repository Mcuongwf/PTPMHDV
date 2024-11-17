package com.duc.manager.repository;

import com.duc.manager.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;



@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

    @Query(value = "SELECT sum(total_money)" +
            "from orders",nativeQuery = true)
    double getRevenue();

    @Query(value = "SELECT \n" +
            "    SUM(CASE WHEN DAY(order_date) BETWEEN 1 AND 7 THEN 1 ELSE 0 END) AS week_1,\n" +
            "    SUM(CASE WHEN DAY(order_date) BETWEEN 8 AND 14 THEN 1 ELSE 0 END) AS week_2,\n" +
            "    SUM(CASE WHEN DAY(order_date) BETWEEN 15 AND 23 THEN 1 ELSE 0 END) AS week_3,\n" +
            "    SUM(CASE WHEN DAY(order_date) > 23 THEN 1 ELSE 0 END) AS week_4\n" +
            "FROM \n" +
            "    orders\n" +
            "WHERE \n" +
            "    MONTH(order_date) = MONTH(CURRENT_DATE())\n" +
            "    AND YEAR(order_date) = YEAR(CURRENT_DATE());", nativeQuery = true)
    Map<String, Object> getNumberOrderInMonth();
}
