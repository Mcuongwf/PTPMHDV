package com.duc.manager.repository;

import com.duc.manager.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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

//    @Query(value = "SELECT \n" +
//            "    SUM(CASE WHEN DAY(order_date) BETWEEN 1 AND 7 THEN total_money ELSE 0 END) AS week_1,\n" +
//            "    SUM(CASE WHEN DAY(order_date) BETWEEN 8 AND 14 THEN total_money ELSE 0 END) AS week_2,\n" +
//            "    SUM(CASE WHEN DAY(order_date) BETWEEN 15 AND 21 THEN total_money ELSE 0 END) AS week_3,\n" +
//            "    SUM(CASE WHEN DAY(order_date) BETWEEN 22 AND 28 THEN total_money ELSE 0 END) AS week_4\n" +
//            "FROM \n" +
//            "    orders\n" +
//            "WHERE \n" +
//            "    MONTH(order_date) = MONTH(CURRENT_DATE())\n" +
//            "    AND YEAR(order_date) = YEAR(CURRENT_DATE());", nativeQuery = true)
//    Map<String, Object> getTotalMoneyInMonth();

    @Query(value = "SELECT \n" +
            "    SUM(CASE WHEN DAY(order_date) BETWEEN 1 AND 7 THEN total_money ELSE 0 END) AS week_1,\n" +
            "    SUM(CASE WHEN DAY(order_date) BETWEEN 8 AND 14 THEN total_money ELSE 0 END) AS week_2,\n" +
            "    SUM(CASE WHEN DAY(order_date) BETWEEN 15 AND 21 THEN total_money ELSE 0 END) AS week_3,\n" +
            "    SUM(CASE WHEN DAY(order_date) BETWEEN 22 AND 28 THEN total_money ELSE 0 END) AS week_4,\n" +
            "    SUM(CASE WHEN DAY(order_date) BETWEEN 29 AND 31 THEN total_money ELSE 0 END) AS week_5\n" +
            "FROM \n" +
            "    orders\n" +
            "WHERE \n" +
            "    MONTH(order_date) = MONTH(CURRENT_DATE())\n" +
            "    AND YEAR(order_date) = YEAR(CURRENT_DATE());", nativeQuery = true)
    Map<String, Object> getTotalMoneyInMonth();



    @Query(value = "SELECT orders.order_id as order_id,orders.status as status, orders.order_date as order_date, customers.customer_id as customer_id, customers.name as customer_name, " +
            "customers.address as customer_address, customers.email as customer_email, " +
            "order_details.id as product_id, order_details.quantity as product_quantity, order_details.total as total " +
            "FROM  Orders orders " +
            "JOIN Customers customers ON orders.customer_id = customers.customer_id " +
            "JOIN order_details order_details ON orders.order_id = order_details.order_id", nativeQuery = true)
    List<Map<String, Object>> findOrderWithDetailsAndCustomer();


    @Query(value = "SELECT orders.order_id as order_id, orders.status as status, orders.order_date as order_date, " +
            " customers.customer_id as customer_id,  customers.name as customer_name, customers.address as customer_address, " +
            "customers.email as customer_email, order_details.id as product_id, order_details.quantity as product_quantity, " +
            "order_details.total as total, products.name as product_name " +  // Thêm thông tin tên sản phẩm
            "FROM Orders orders " +
            "JOIN Customers customers ON orders.customer_id = customers.customer_id " +
            "JOIN order_details order_details ON orders.order_id = order_details.order_id " +
            "JOIN products products ON order_details.id = products.id " +  // JOIN với bảng sản phẩm
            "WHERE orders.order_id = :orderId", nativeQuery = true)
    List<Map<String, Object>> findOrderDetailsByOrderId(@Param("orderId") Long orderId);







}
