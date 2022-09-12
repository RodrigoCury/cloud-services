package br.dev.rodrigocury.fraud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class FraudApplication

fun main(args: Array<String>) {
    runApplication<FraudApplication>(*args)
}
