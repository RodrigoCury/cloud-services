package br.dev.rodrigocury.customer.dtos

data class CustomerRegistrationRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
)
