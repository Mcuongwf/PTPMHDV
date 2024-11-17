package com.duc.manager.repository;

import com.duc.manager.entity.Products;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Repository
public interface ProductRepository extends JpaRepository<Products,Integer>{

    @Query(value = "SELECT  products.id as id ,products.name as name," +
            "products.price as price ,sum(order_details.quantity) as quantity ," +
            "products.price*sum(order_details.quantity)as total\n" +
            "FROM     order_details JOIN\n" +
            "                  products ON order_details.id = products.id\n" +
            "group by products.name,order_details.id,products.id\n" +
            "order by sum(order_details.quantity) desc\n" +
            "limit 10", nativeQuery = true)
    List<Map<String, Object>> getTop5();


    @Query(value = "SELECT COUNT(*) AS SoSanPham\n" +
            "FROM products\n" +
            "WHERE YEAR(update_date) = YEAR(CURDATE())\n" +
            "  AND MONTH(update_date) = MONTH(CURDATE());",nativeQuery = true)
    int getProductInMonth();

}
