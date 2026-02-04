package br.com.fiap.adj8.phase5.prioritas.application.service.mapper

import br.com.fiap.adj8.phase5.prioritas.common.event.TriageNotificationEvent
import br.com.fiap.adj8.phase5.prioritas.common.event.VitalSignsPayload
import br.com.fiap.adj8.phase5.prioritas.domain.model.Triage
import br.com.fiap.adj8.phase5.prioritas.domain.model.VitalSigns
import java.util.*

fun Triage.toTriageNotificationEvent(patientId: UUID, vitalSigns: VitalSigns) =
    TriageNotificationEvent(
        triageId = id,
        assessedAt = assessedAt,
        riskLevel = riskLevel.name,
        riskColor = riskLevel.colorName,
        patientId = patientId,
        vitalSigns = vitalSigns.toVitalSignsPayload()
    )

private fun VitalSigns.toVitalSignsPayload() =
    VitalSignsPayload(
        temperature = temperature,
        heartRate = heartRate,
        oxygenSaturation = oxygenSaturation,
        hasChestPain = hasChestPain,
        systolicPressure = systolicPressure,
        diastolicPressure = diastolicPressure,
    )