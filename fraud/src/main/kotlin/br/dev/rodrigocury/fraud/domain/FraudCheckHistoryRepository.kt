package br.dev.rodrigocury.fraud.domain

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface FraudCheckHistoryRepository: ReactiveMongoRepository<FraudCheckHistory, String>