package com.jimmycasta.pizzeria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order",nullable = false)
    private Integer idOrder;

    @Column(name = "id_customer",nullable = false,length = 15)
    private String idCustomer;

    @Column(nullable = false, columnDefinition = "Date")
    private LocalDate date;

    @Column(nullable = false, columnDefinition = "Double")
    private Double total;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String method;

    @Column(length = 200)
    private String additional_notes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", insertable = false, updatable = false)
    @JsonIgnore
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItemEntity> items;


}
