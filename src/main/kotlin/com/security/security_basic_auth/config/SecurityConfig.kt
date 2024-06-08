package com.security.security_basic_auth.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "spring.security")
class SecurityConfig {

    lateinit var username: String
    lateinit var password: String
    lateinit var role: String

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorize ->
                authorize.requestMatchers("/private/hello").authenticated()
                authorize.anyRequest().permitAll()
            }
            .httpBasic(Customizer.withDefaults())
            .csrf {
                csrf -> csrf.disable()
            }

        return http.build()
    }

    @Bean
    fun encoder() : BCryptPasswordEncoder {
        return BCryptPasswordEncoder();
    }

    @Bean
    fun userDetailsInMemory() : InMemoryUserDetailsManager {
        val userDetails : UserDetails = User.builder()
            .username(username)
            .password(encoder().encode(password))
            .roles(role)
            .build();
        return InMemoryUserDetailsManager(userDetails)
    }
}