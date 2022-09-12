package br.dev.rodrigocury.customer.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Customer {
    @Id
    var id: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var email: String? = null
}
