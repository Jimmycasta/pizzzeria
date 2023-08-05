package com.jimmycasta.pizzeria.repositories;

import com.jimmycasta.pizzeria.entities.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PizzaPageRepository extends PagingAndSortingRepository<PizzaEntity,Integer> {

    Page<PizzaEntity> findByAvailableTrue(Pageable pageable);
}
