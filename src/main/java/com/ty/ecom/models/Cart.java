package com.ty.ecom.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_info")
public class Cart {

    @Id
    @SequenceGenerator(name = "cart_seq", initialValue = 100, allocationSize = 2)
    @GeneratedValue(generator = "cart_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "cart_id", unique = true, nullable = false, precision = 10)
    private Integer cartId;
    @Column(name = "product_count")
    private Integer productCount;
    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "cart")
    private Set<Product> productList;

}
