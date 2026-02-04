package br.com.fiap.adj8.phase5.prioritas.infra.config

import br.com.fiap.adj8.phase5.prioritas.application.port.out.SaveTriagePort
import br.com.fiap.adj8.phase5.prioritas.application.port.out.SendTriageEventPort
import br.com.fiap.adj8.phase5.prioritas.application.service.PerformTriageService
import br.com.fiap.adj8.phase5.prioritas.domain.rules.impl.EmergencyRule
import br.com.fiap.adj8.phase5.prioritas.domain.rules.impl.UrgentRule
import br.com.fiap.adj8.phase5.prioritas.domain.rules.impl.VeryUrgentRule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TriageBeanConfiguration {
    @Bean
    fun performTriageUseCase(
        saveTriagePort: SaveTriagePort,
        sendTriageEventPort: SendTriageEventPort
    ): PerformTriageService {
        val rules = listOf(
            EmergencyRule(),
            VeryUrgentRule(),
            UrgentRule()
        )

        return PerformTriageService(saveTriagePort, rules, sendTriageEventPort)
    }
}