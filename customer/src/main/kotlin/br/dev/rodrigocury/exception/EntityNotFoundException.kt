package br.dev.rodrigocury.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class EntityNotFoundException(override val message: String): ResponseStatusException(HttpStatus.NOT_FOUND, message)