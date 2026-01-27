package br.com.fiap.adj8.phase5.prioritas.infra.adapter.`in`.web.contract

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

interface TriageRequestDocs {
    @get:Schema(description = "ID único do paciente", example = "123e4567-e89b-12d3-a456-426614174000")
    val patientId: UUID

    @get:Schema(description = "Temperatura corporal em graus Celsius", example = "37.5")
    val temperature: Double?

    @get:Schema(description = "Batimentos cardíacos por minuto (BPM)", example = "90")
    val heartRate: Int?

    @get:Schema(description = "Saturação de oxigênio (%)", example = "98")
    val oxygenSaturation: Int?

    @get:Schema(description = "Indica se o paciente relata dor no peito", example = "false")
    val hasChestPain: Boolean

    @get:Schema(description = "Pressão Sistólica (mmHg)", example = "120")
    val systolicPressure: Int?

    @get:Schema(description = "Pressão Diastólica (mmHg)", example = "80")
    val diastolicPressure: Int?
}