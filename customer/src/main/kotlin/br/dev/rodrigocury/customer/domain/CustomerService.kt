package br.dev.rodrigocury.customer.domain

import br.dev.rodrigocury.exception.EntityNotFoundException
import br.dev.rodrigocury.customer.dtos.CustomerRegistrationRequest
import br.dev.rodrigocury.exception.FraudlentCustomerException
import br.dev.rodrigocury.customer.views.CustomerResponse
import br.dev.rodrigocury.customer.views.FraudlentResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class CustomerService(
    private val repo: CustomerRepo,
    private val restTemplate: RestTemplate
) {
    fun registerCustomer(customerRequest: CustomerRegistrationRequest) =
        Customer()
            .apply {
                firstName = customerRequest.firstName
                lastName = customerRequest.lastName
                email = customerRequest.email
            }.let { customer ->
                repo.save(customer)
                    .publishOn(Schedulers.boundedElastic())
                    .map { savedCustomer ->
                        val fraudlentResponse = restTemplate.getForObject(
                            "http://FRAUD/fraud/{customerId}",
                            FraudlentResponse::class.java,
                            savedCustomer.id
                        )

                        if (fraudlentResponse?.fraudulent == true)
                            throw FraudlentCustomerException("Customer is Fraudulent")

                        CustomerResponse(
                            savedCustomer.id,
                            savedCustomer.firstName,
                            savedCustomer.lastName,
                            savedCustomer.email
                        )
                    }
            }


    fun getCustomer(id: String) =
        repo
            .findById(id)
            .map {CustomerResponse(it.id, it.firstName, it.lastName, it.email)}
            .switchIfEmpty(Mono.error(
                    EntityNotFoundException("Customer Not Found")
            ))

    fun getCustomers() =
        repo
            .findAll()
            .map { CustomerResponse(it.id, it.firstName, it.lastName, it.email) }

}
