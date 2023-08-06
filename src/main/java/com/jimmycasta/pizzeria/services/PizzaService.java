package com.jimmycasta.pizzeria.services;

import com.jimmycasta.pizzeria.Exception.EmailApiException;
import com.jimmycasta.pizzeria.entities.PizzaEntity;
import com.jimmycasta.pizzeria.repositories.PizzaPageRepository;
import com.jimmycasta.pizzeria.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaPageRepository pizzaPageRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPageRepository pizzaPageRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPageRepository = pizzaPageRepository;
    }

    public Page<PizzaEntity> getAll(int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
        return pizzaPageRepository.findAll(pageRequest);
    }

    public Page<PizzaEntity> getAvailable(int pageNumber, int pageSize, String sortBy) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return pizzaPageRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity getByName(String name) {
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public PizzaEntity get(int idPizza) {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return pizzaRepository.save(pizza);
    }

    public void delete(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);

    }

    @Transactional
    public void setUpdatePrice(int idPizza, double newPrice) {
        pizzaRepository.updatePrice(idPizza, newPrice);
    }

    public boolean exists(int idPizza) {
        return this.pizzaRepository.existsById(idPizza);

    }
}
