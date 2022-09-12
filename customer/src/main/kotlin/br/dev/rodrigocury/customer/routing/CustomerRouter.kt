package br.dev.rodrigocury.customer.routing

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class CustomerRouter {

    @Bean
    fun customerRouting(handler: CustomerHandler) = router {
        "/customer".nest {
            GET("", handler::getCustomers)
            GET("/{id}", handler::getCustumer)
            POST("", handler::registerCustomer)
        }
    }

}