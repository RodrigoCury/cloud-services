package br.dev.rodrigocury.fraud.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class FraudCheckHistory {

    @Id
    var id: String? = null
    var customerId: String? = null
    var isFraudster: Boolean = false
    var createdAt: LocalDateTime = LocalDateTime.now()

}