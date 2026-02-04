package br.com.fiap.adj8.phase5.prioritas.infra.adapter.out.persistence

import br.com.fiap.adj8.phase5.prioritas.application.port.out.SaveTriagePort
import br.com.fiap.adj8.phase5.prioritas.domain.model.Triage
import org.springframework.stereotype.Component

@Component
class TriagePersistenceAdapter(
    private val repository: SpringDataTriageRepository
) : SaveTriagePort {

    override fun save(triage: Triage): Triage {
        val entity = TriageEntity(
            id = triage.id,
            patientId = triage.patientId,
            riskLevel = triage.riskLevel,
            assessedAt = triage.assessedAt,
            temperature = triage.vitalSigns.temperature,
            heartRate = triage.vitalSigns.heartRate,
            oxygenSaturation = triage.vitalSigns.oxygenSaturation,
            hasChestPain = triage.vitalSigns.hasChestPain,
            systolicPressure = triage.vitalSigns.systolicPressure,
            diastolicPressure = triage.vitalSigns.diastolicPressure
        )
        repository.save(entity)
        return triage
    }
}