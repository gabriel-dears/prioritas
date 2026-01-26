package br.com.fiap.adj8.phase5.prioritas.infra.adapter.`in`.web.dto

import br.com.fiap.adj8.phase5.prioritas.domain.model.RiskLevel
import br.com.fiap.adj8.phase5.prioritas.domain.model.Triage
import java.time.LocalDateTime
import java.util.UUID

data class TriageResponse(
    val triageId: UUID,
    val patientId: UUID,
    val riskLevel: RiskLevel, // Retornar o Enum completo é bom, o JSON mostra o nome
    val riskColor: String,
    val waitTimeMinutes: Int,
    val assessedAt: LocalDateTime
) {
    // Construtor secundário para criar a partir do Domínio
    companion object {
        fun fromDomain(triage: Triage): TriageResponse {
            return TriageResponse(
                triageId = triage.id,
                patientId = triage.patientId,
                riskLevel = triage.riskLevel,
                riskColor = triage.riskLevel.colorName,
                waitTimeMinutes = triage.riskLevel.maxWaitTimeMinutes,
                assessedAt = triage.assessedAt
            )
        }
    }
}