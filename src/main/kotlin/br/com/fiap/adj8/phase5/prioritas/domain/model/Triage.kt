package br.com.fiap.adj8.phase5.prioritas.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class Triage(
    val id: UUID = UUID.randomUUID(),
    val patientId: UUID,
    val vitalSigns: VitalSigns,
    val riskLevel: RiskLevel,
    val assessedAt: LocalDateTime = LocalDateTime.now()
)