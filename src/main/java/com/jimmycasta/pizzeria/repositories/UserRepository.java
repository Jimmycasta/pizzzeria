package com.jimmycasta.pizzeria.repositories;

import com.jimmycasta.pizzeria.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,String> {
}
