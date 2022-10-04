package com.example.clinicaodontologica.repository;

import com.example.clinicaodontologica.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDomicilioRepository extends JpaRepository<Domicilio, Long> {
}
