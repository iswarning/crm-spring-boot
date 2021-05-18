package com.example.demo.repositories;

import com.example.demo.entities.Juridical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuridicalRepository extends JpaRepository<Juridical, Integer> {
}
