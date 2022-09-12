package br.dev.rodrigocury.customer.routing

import br.dev.rodrigocury.customer.domain.CustomerService
import br.dev.rodrigocury.customer.dtos.CustomerRegistrationRequest
import br.dev.rodrigocury.customer.views.CustomerResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class CustomerHandler {

    @Autowired
    lateinit var service: CustomerService

    fun registerCustomer(serverRequest: ServerRequest) =
        serverRequest
            .bodyToMono(CustomerRegistrationRequest::class.java)
            .flatMap { customer ->
                ServerResponse
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(service.registerCustomer(customer), CustomerResponse::class.java)
            }


    fun getCustumer(serverRequest: ServerRequest) =
        serverRequest
            .pathVariable("id")
            .let { id ->
                ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(service.getCustomer(id), CustomerResponse::class.java)
                    .switchIfEmpty(ServerResponse.notFound().build())
            }


    fun getCustomers(serverRequest: ServerRequest) =
        ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.getCustomers(), CustomerResponse::class.java)

}