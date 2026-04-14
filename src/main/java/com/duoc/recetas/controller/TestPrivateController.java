package com.duoc.recetas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestPrivateController {

    @GetMapping("/api/private/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Private route accessed successfully");
    }
} 