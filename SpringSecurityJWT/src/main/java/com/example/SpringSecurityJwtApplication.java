package com.example;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.models.ERole;
import com.example.models.RoleEntity;
import com.example.models.UserEntity;
import com.example.repositories.UserRepository;

@SpringBootApplication
public class SpringSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Bean
	CommandLineRunner init() {
		return args -> {

			UserEntity userEntity = UserEntity.builder()
					.email("alan@mail.com")
					.username("alan")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder()
						.name(ERole.valueOf(ERole.ADMIN.name()))
						.build()))
					.build();
			
			UserEntity userEntity2 = UserEntity.builder()
					.email("jaime@mail.com")
					.username("jaime")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder()
						.name(ERole.valueOf(ERole.USER.name()))
						.build()))
					.build();

			UserEntity userEntity3 = UserEntity.builder()
					.email("antonio@mail.com")
					.username("antonio")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder()
						.name(ERole.valueOf(ERole.INVITED.name()))
						.build()))
					.build();

			userRepository.save(userEntity);
			userRepository.save(userEntity2);
			userRepository.save(userEntity3);
		};
	}
	
	
	
	

}
