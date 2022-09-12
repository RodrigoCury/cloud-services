package br.dev.rodrigocury.fraud.domain

import br.dev.rodrigocury.fraud.vo.FraudlentResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class FraudCheckHistoryService(
    private val fraudCheckHistoryRepository: FraudCheckHistoryRepository
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun isFraudlentCustomer(customerId: String) = Mono
        .just(FraudCheckHistory().apply {
            this.customerId = customerId
            this.isFraudster = false
        }).flatMap { history ->
            log.info("FraudCheck done!")
            fraudCheckHistoryRepository.save(history).map {
                FraudlentResponse(it.isFraudster)
            }
        }
}