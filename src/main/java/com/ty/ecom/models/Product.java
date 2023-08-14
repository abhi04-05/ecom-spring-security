package com.ty.ecom.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_info")
public class Product {

    @Id
    @SequenceGenerator(name = "prod_seq", initialValue = 100, allocationSize = 2)
    @GeneratedValue(generator = "prod_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "product_id", unique = true, nullable = false, precision = 10)
    private Integer productId;
    @Column(name = "product_name", unique = true, nullable = false)
    private String productName;
    @Column(name = "product_price")
    private Double productPrice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

}
