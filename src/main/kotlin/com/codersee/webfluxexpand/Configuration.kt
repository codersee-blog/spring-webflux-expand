package com.codersee.webfluxexpand

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class Configuration {

    @Bean
    fun webClient(): WebClient =
        WebClient.builder()
            .baseUrl("http://localhost:8080")
            .build()
}