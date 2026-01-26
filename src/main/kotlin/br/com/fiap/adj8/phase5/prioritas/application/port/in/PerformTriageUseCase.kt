package br.com.fiap.adj8.phase5.prioritas.application.port.`in`

import br.com.fiap.adj8.phase5.prioritas.domain.model.Triage
import br.com.fiap.adj8.phase5.prioritas.domain.model.VitalSigns
import java.util.UUID

interface PerformTriageUseCase {
    fun execute(patientId: UUID, vitalSigns: VitalSigns): Triage
}