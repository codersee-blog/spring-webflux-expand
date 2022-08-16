package com.codersee.webfluxexpand

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.*

@Service
class ExampleService(
    private val webClient: WebClient
) {

    operator fun invoke() {
        performRequest("")
            .expand { response ->
                performRequest(token = response.nextPageToken)
            }
            .subscribe(System.out::println)
    }

    private fun performRequest(token: String?): Mono<ExampleController.ExampleDto> =
        webClient
            .get()
            .uri { builder ->
                builder.path("/api/example")
                    .queryParamIfPresent("token", Optional.ofNullable(token))
                    .build()
            }
            .retrieve()
            .bodyToMono(ExampleController.ExampleDto::class.java)
}