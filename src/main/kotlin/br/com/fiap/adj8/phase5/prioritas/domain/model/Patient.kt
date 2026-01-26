package br.com.fiap.adj8.phase5.prioritas.domain.model

import java.util.UUID

data class Patient(
    val id: UUID = UUID.randomUUID(),
    val cpf: String,
    val name: String
) {
    init {
        require(cpf.isNotBlank()) { "CPF cannot be empty" }
        require(name.isNotBlank()) { "Name cannot be empty" }
    }
}