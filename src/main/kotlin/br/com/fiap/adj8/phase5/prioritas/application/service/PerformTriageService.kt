package br.com.fiap.adj8.phase5.prioritas.application.service

import br.com.fiap.adj8.phase5.prioritas.application.port.`in`.PerformTriageUseCase
import br.com.fiap.adj8.phase5.prioritas.application.port.out.SaveTriagePort
import br.com.fiap.adj8.phase5.prioritas.application.port.out.SendTriageEventPort
import br.com.fiap.adj8.phase5.prioritas.application.service.mapper.toTriageNotificationEvent
import br.com.fiap.adj8.phase5.prioritas.domain.model.RiskLevel
import br.com.fiap.adj8.phase5.prioritas.domain.model.Triage
import br.com.fiap.adj8.phase5.prioritas.domain.model.VitalSigns
import br.com.fiap.adj8.phase5.prioritas.domain.rules.TriageRule
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class PerformTriageService(
    private val saveTriagePort: SaveTriagePort,
    private val triageRules: List<TriageRule>,
    private val sendTriageEventPort: SendTriageEventPort
) : PerformTriageUseCase {

    private val logger = LoggerFactory.getLogger(PerformTriageService::class.java)

    @Transactional
    override fun execute(patientId: UUID, vitalSigns: VitalSigns): Triage {
        logger.info("🔍 [START] Iniciando análise de triagem para Paciente ID: $patientId")
        logger.info("Sinais Vitais: {}", vitalSigns)

        // 1. Padrão Strategy
        var selectedRisk = RiskLevel.STANDARD
        var appliedRuleName = "DefaultStandardRule"

        for (rule in triageRules) {
            if (rule.matches(vitalSigns)) {
                selectedRisk = rule.getRiskLevel()
                appliedRuleName = rule::class.simpleName ?: "UnknownRule"

                logger.info("✅ Regra correspondente encontrada: $appliedRuleName")
                break
            }
        }

        val triage = Triage(
            patientId = patientId,
            vitalSigns = vitalSigns,
            riskLevel = selectedRisk
        )

        // 3. Persistência
        val savedTriage = saveTriagePort.save(triage)

        logger.info("📊 Risco Definido: $selectedRisk (Determinado por: $appliedRuleName)")

        if( RiskLevel.EMERGENCY == selectedRisk ) {
            logger.info("Enviando notificação async...")
            sendTriageEventPort.send(triage.toTriageNotificationEvent(patientId, vitalSigns))
        }

        logger.info("💾 [END] Triagem persistida com sucesso. ID da Triagem: ${savedTriage.id}")

        return savedTriage
    }
}