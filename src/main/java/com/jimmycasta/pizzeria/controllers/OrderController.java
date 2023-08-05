package com.jimmycasta.pizzeria.controllers;

import com.jimmycasta.pizzeria.entities.OrderEntity;
import com.jimmycasta.pizzeria.projections.OrderSummary;
import com.jimmycasta.pizzeria.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll() {
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders() {
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutSideOrders() {
        return ResponseEntity.ok(this.orderService.getOutSideOrders());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String id) {
        return ResponseEntity.ok(this.orderService.getCustomerOrder(id));

    }

    @GetMapping("/summary/{idOrder}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable("idOrder") int idOrder) {
        return ResponseEntity.ok(this.orderService.getSummary(idOrder));

    }
}
