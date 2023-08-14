package com.ty.ecom.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role_info")
public class Role {

    @Id
    @SequenceGenerator(name = "role_seq", initialValue = 100, allocationSize = 2)
    @GeneratedValue(generator = "role_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id", unique = true, nullable = false, precision = 10)
    private Integer roleId;
    @Column(name = "role_name")
    private String roleName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "role")
    private Set<Customer> customerList;

}
