package com.jimmycasta.pizzeria.repositories;

import com.jimmycasta.pizzeria.dto.UpdatePizzaPrecioDto;
import com.jimmycasta.pizzeria.entities.PizzaEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    @Query(value = "UPDATE pizza SET price = :newPrice WHERE id_pizza = :idPizza", nativeQuery = true)
    @Modifying
    void  updatePrice(@Param("idPizza") int idPizza, @Param("newPrice") double newPrice);
 }
