package br.com.fiap.adj8.phase5.prioritas.infra.adapter.out.persistence

import br.com.fiap.adj8.phase5.prioritas.application.port.out.SaveTriagePort
import br.com.fiap.adj8.phase5.prioritas.domain.model.Triage
import org.springframework.stereotype.Component

@Component
class TriagePersistenceAdapter(
    private val repository: SpringDataTriageRepository
) : SaveTriagePort {

    override fun save(triage: Triage): Triage {
        // 1. Converte Domain -> JPA Entity
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

        // 2. Salva no Banco
        val savedEntity = repository.save(entity)

        // 3. Retorna o Domain (neste caso, como o ID não muda, retornamos o próprio objeto de entrada
        // ou poderíamos reconverter se houvesse mudança de estado gerada pelo banco)
        return triage
    }
}