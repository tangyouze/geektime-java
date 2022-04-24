package com.tangyouze.week08.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author tyz
 */
@Entity
@Table(name = "product_order")
@Getter
@Setter
public class ProductOrder implements Serializable {


    @Id
    @GeneratedValue
    private Long orderId;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "sku_id")
    private Long skuId;

    @Column(name = "count")
    private Long count;

    public ProductOrder() {
        super();
    }


}

