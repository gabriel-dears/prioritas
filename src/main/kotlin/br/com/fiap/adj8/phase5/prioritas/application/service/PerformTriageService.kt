package br.com.fiap.adj8.phase5.prioritas.application.service

import br.com.fiap.adj8.phase5.prioritas.application.port.`in`.PerformTriageUseCase
import br.com.fiap.adj8.phase5.prioritas.application.port.out.SaveTriagePort
import br.com.fiap.adj8.phase5.prioritas.domain.model.RiskLevel
import br.com.fiap.adj8.phase5.prioritas.domain.model.Triage
import br.com.fiap.adj8.phase5.prioritas.domain.model.VitalSigns
import br.com.fiap.adj8.phase5.prioritas.domain.rules.TriageRule
import java.util.UUID

class PerformTriageService(
    private val saveTriagePort: SaveTriagePort,
    private val triageRules: List<TriageRule>
) : PerformTriageUseCase {

    override fun execute(patientId: UUID, vitalSigns: VitalSigns): Triage {
        // 1. Padrão Strategy: Itera sobre as regras para encontrar a primeira correspondência
        // Se nenhuma regra bater, o padrão é STANDARD (Verde)
        var selectedRisk = RiskLevel.STANDARD

        for (rule in triageRules) {
            if (rule.matches(vitalSigns)) {
                selectedRisk = rule.getRiskLevel()
                // Paramos na primeira regra que der match, pois a lista será ordenada por gravidade
                break
            }
        }

        // 2. Criação da Entidade de Domínio
        val triage = Triage(
            patientId = patientId,
            vitalSigns = vitalSigns,
            riskLevel = selectedRisk
        )

        // 3. Persistência
        return saveTriagePort.save(triage)
    }
}