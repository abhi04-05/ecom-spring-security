package com.ty.ecom.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_info")
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_seq", initialValue = 100, allocationSize = 2)
    @GeneratedValue(generator = "customer_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "customer_id", unique = true, nullable = false, precision = 10)
    private Integer customerId;
    @Column(name = "customer_user_name", unique = true, nullable = false)
    private String customerUserName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "customer_password")
    private String password;
    @Column(name = "customer_email", unique = true, nullable = false)
    private String email;
    @Column(name = "mobile_number", unique = true, nullable = false)
    private Long mobileNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "is_locked")
    private Boolean isLocked;
    @Column(name = "local_timestamp")
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}
