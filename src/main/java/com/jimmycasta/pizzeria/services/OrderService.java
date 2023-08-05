package com.jimmycasta.pizzeria.services;

import com.jimmycasta.pizzeria.entities.OrderEntity;
import com.jimmycasta.pizzeria.projections.OrderSummary;
import com.jimmycasta.pizzeria.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = this.orderRepository.findAll();
        orders.forEach(o -> System.out.println(o.getCustomer().getName()));
        return orders;
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDate today = LocalDate.now();
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutSideOrders() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrder(String idCustomer) {
        return orderRepository.findByCustomerOrders(idCustomer);

    }

    public OrderSummary getSummary(int idOrder) {
        return this.orderRepository.getSummary(idOrder);
    }
}
