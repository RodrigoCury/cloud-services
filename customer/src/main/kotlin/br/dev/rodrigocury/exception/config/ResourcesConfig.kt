package br.dev.rodrigocury.exception.config

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ResourcesConfig {

    @Bean
    fun propertiesResources(): WebProperties.Resources {
        return WebProperties.Resources()
    }

}