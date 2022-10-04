package com.example.clinicaodontologica.security_configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private IUserRepository userRepository;

    @Autowired
    public DataLoader(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password1 = passwordEncoder.encode("user");
        String password2 = passwordEncoder.encode("admin");

        userRepository.save(new Usuario("User", "user", "user@mail.com", password1, UsuarioRol.USER));
        userRepository.save(new Usuario("Admin", "admin", "admin@mail.com", password2, UsuarioRol.ADMIN));
    }
}