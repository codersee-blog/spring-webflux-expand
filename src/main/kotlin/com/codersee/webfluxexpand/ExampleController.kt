package com.codersee.webfluxexpand

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api")
class ExampleController(
    private val service: ExampleService
) {

    data class ExampleDto(val name: String, val nextPageToken: String?)

    val exampleList = mapOf(
        "" to ExampleDto("Name-1", "token-2"),
        "token-2" to ExampleDto("Name-2", "token-3"),
        "token-3" to ExampleDto("Name-3", "token-4"),
        "token-4" to ExampleDto("Name-4", "token-5"),
        "token-5" to ExampleDto("Name-5", null)
    )

    @GetMapping("/example")
    fun getExample(@RequestParam("token") pageToken: String?): Mono<ExampleDto> =
        pageToken?.let { token ->
            Mono.just(exampleList[token]!!)
        } ?: Mono.empty()


    @GetMapping("/test")
    fun testTheEndpoint() {
        service()
    }

}