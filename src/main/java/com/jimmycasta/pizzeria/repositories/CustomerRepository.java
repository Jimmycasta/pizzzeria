package com.jimmycasta.pizzeria.repositories;

import com.jimmycasta.pizzeria.entities.CustomerEntity;
import com.jimmycasta.pizzeria.entities.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, String> {

    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phone")
    CustomerEntity findByPhoneNumber(@Param("phone") String phone);

}
