package com.example.demo.repository;

import com.example.demo.model.database.jpa.Taxes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends JpaRepository<Taxes, Long> {

}