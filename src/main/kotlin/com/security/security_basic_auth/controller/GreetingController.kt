package com.security.security_basic_auth.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {

    @GetMapping("/public/hello")
    fun publicHelloResource() : ResponseEntity<String> {
        return ResponseEntity.ok("Olá, esse endpoint é público.");
    }

    @GetMapping("private/hello")
    fun privateHelloResource() : ResponseEntity<String> {
        return ResponseEntity.ok("Olá, esse endpoint é privado.")
    }
}