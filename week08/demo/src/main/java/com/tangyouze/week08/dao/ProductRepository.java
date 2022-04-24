package com.tangyouze.week08.dao;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<ProductOrder, Long> {
    ProductOrder findByUserName(String userName);
}

