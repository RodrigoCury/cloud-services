package br.dev.rodrigocury.fraud.routing

import br.dev.rodrigocury.fraud.domain.FraudCheckHistoryService
import br.dev.rodrigocury.fraud.vo.FraudlentResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class FraudHandler(private val service: FraudCheckHistoryService) {

    fun registerFraudCheck(request: ServerRequest) = ServerResponse
        .status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .eTag("banana")
        .body(
            service.isFraudlentCustomer(request.pathVariable("customerId")),
            FraudlentResponse::class.java
        )

}