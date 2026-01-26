package br.com.fiap.adj8.phase5.prioritas.infra.adapter.`in`.web.dto

import br.com.fiap.adj8.phase5.prioritas.domain.model.VitalSigns
import java.util.UUID

data class TriageRequest(
    val patientId: UUID,
    val temperature: Double?,
    val heartRate: Int?,
    val oxygenSaturation: Int?,
    val hasChestPain: Boolean = false,
    val systolicPressure: Int?,
    val diastolicPressure: Int?
) {
    // Função auxiliar para converter DTO -> Value Object do Domínio
    fun toDomain(): VitalSigns {
        return VitalSigns(
            temperature = temperature,
            heartRate = heartRate,
            oxygenSaturation = oxygenSaturation,
            hasChestPain = hasChestPain,
            systolicPressure = systolicPressure,
            diastolicPressure = diastolicPressure
        )
    }
}