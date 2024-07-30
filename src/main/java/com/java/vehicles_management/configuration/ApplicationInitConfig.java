package com.java.vehicles_management.configuration;

import com.java.vehicles_management.entity.Users;
import com.java.vehicles_management.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("ALL")
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()){
                Users user = Users.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .build();
                userRepository.save(user);
            }
        };
    }
}
