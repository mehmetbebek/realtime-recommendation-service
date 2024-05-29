package com.hepsiburada.realtime_recommendation_service.repository;

import com.hepsiburada.realtime_recommendation_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = """
            select p.category_id,
                   p.product_id,
                   count(distinct o.user_id) as distinct_users
            from orders o
                     inner join order_items oi on
                oi.order_id = o.order_id
                     inner join products p on
                p.product_id = oi.product_id
            where o.order_date >= current_date - interval '1 month'
            group by p.category_id,
                     p.product_id
            order by distinct_users desc,
                     p.category_id,
                     p.product_id""", nativeQuery = true)
    List<Object[]> findTopTenProductsBoughtLastMonth();


    @Query(value = """
            with top_3_categories as (select p.category_id
                                                         from "product-view" pv
                                                                  inner join products p on p.product_id = pv.product_id
                                                         where pv.user_id = :userId
                                                         group by p.category_id
                                                         limit 3)
                               select p.category_id,
                                      p.product_id,
                                      count(distinct o.user_id) as distinct_users
                               from orders o
                                        inner join order_items oi on
                                   oi.order_id = o.order_id
                                        inner join products p on
                                   p.product_id = oi.product_id
                               where o.order_date >= current_date - interval '1 month'
                                 and p.category_id in (select * from top_3_categories)
                               group by p.category_id,
                                        p.product_id
                               order by distinct_users desc,
                                        p.category_id,
                                        p.product_id;""", nativeQuery = true)
    List<Object[]> findTopTenProductsBoughtLastMonth(String userId);

}
