package com.jimmycasta.pizzeria.controllers;

import com.jimmycasta.pizzeria.entities.PizzaEntity;
import com.jimmycasta.pizzeria.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int pageNumber,
                                                    @RequestParam(defaultValue = "8") int pageSize) {
        return ResponseEntity.ok(this.pizzaService.getAll(pageNumber, pageSize));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> get(@PathVariable("idPizza") int idPizza) {
        return ResponseEntity.ok(this.pizzaService.get(idPizza));
    }

    @GetMapping("/available")
    public Page<PizzaEntity> getAvailable(@RequestParam(defaultValue = "0") int pageNumber,
                                          @RequestParam(defaultValue = "8") int pageSize,
                                          @RequestParam(defaultValue = "price") String sortBy) {
        return this.pizzaService.getAvailable(pageNumber, pageSize, sortBy);

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByname(@PathVariable String name) {
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() != null && this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza) {
        if (this.pizzaService.exists(idPizza)) {
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaService.getWithout(ingredient));

    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestParam int idPizza, @RequestParam double newPrice) {
        this.pizzaService.setUpdatePrice(idPizza, newPrice);
        return ResponseEntity.ok().build();
    }
}
