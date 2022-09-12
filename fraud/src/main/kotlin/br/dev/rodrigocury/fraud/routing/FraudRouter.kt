package br.dev.rodrigocury.fraud.routing

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Configuration
class FraudRouter(
    val handler: FraudHandler
) {

    @Bean
    fun fraudCheckHistoryRoute() = router {
        "/fraud".nest {
            GET("/{customerId}", handler::registerFraudCheck)
        }

    }
}