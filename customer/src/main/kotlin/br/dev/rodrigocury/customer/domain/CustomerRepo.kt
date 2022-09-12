package br.dev.rodrigocury.customer.domain

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface CustomerRepo: ReactiveMongoRepository<Customer, String>