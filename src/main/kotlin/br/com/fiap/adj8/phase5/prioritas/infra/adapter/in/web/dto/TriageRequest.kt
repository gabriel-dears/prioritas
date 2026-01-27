package br.com.fiap.adj8.phase5.prioritas.infra.adapter.`in`.web.dto

import br.com.fiap.adj8.phase5.prioritas.domain.model.VitalSigns
import br.com.fiap.adj8.phase5.prioritas.infra.adapter.`in`.web.contract.TriageRequestDocs
import java.util.*

data class TriageRequest(
    override val patientId: UUID,
    override val temperature: Double?,
    override val heartRate: Int?,
    override val oxygenSaturation: Int?,
    override val hasChestPain: Boolean = false,
    override val systolicPressure: Int?,
    override val diastolicPressure: Int?
) : TriageRequestDocs {

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