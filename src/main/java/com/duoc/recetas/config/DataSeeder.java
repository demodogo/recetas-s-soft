package com.duoc.recetas.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.duoc.recetas.model.Recipe;
import com.duoc.recetas.model.User;
import com.duoc.recetas.repository.RecipeRepository;
import com.duoc.recetas.repository.UserRepository;

@Configuration
public class DataSeeder {
  
  @Bean
  CommandLineRunner seedDate(UserRepository userRepository, RecipeRepository recipeRepository, PasswordEncoder passwordEncoder) {
    return args -> {
      if (userRepository.count() == 0) {
        String hashedPassword = passwordEncoder.encode("123456");
        User user1 = new User();
        user1.setEmail("admin@test.com");
        user1.setPassword(hashedPassword);
        user1.setFullName("Administrador Plataforma");
        user1.setEnabled(true);
        userRepository.save(user1);

        User user2 = new User();
        user2.setEmail("user@test.com");
        user2.setPassword(hashedPassword);
        user2.setFullName("Usuario Normal");
        user2.setEnabled(true);
        userRepository.save(user2);

        User user3 = new User();
        user3.setEmail("maca@test.com");
        user3.setPassword(hashedPassword);
        user3.setFullName("Macarena Munoz");
        user3.setEnabled(true);
        userRepository.save(user3);

        Recipe recipe1 = new Recipe();
        recipe1.setTitle("Pasta Alfredo");
        recipe1.setDescription("Receta cremosa con ajo, queso y crema.");
        recipe1.setUser(user1);
        recipeRepository.save(recipe1);

        Recipe recipe2 = new Recipe();
        recipe2.setTitle("Tacos Caseros");
        recipe2.setDescription("Tacos con carne, cebolla, tomate y salsa.");
        recipe2.setUser(user2);
        recipeRepository.save(recipe2);

      }
    };
  }
}
