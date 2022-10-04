package com.example.clinicaodontologica.security_configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}
