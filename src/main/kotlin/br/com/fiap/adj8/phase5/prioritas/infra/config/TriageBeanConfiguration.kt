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
        // AQUI ESTÁ O SEGREDO DA PRIORIDADE!
        // A ordem desta lista define qual regra é testada primeiro.
        // Deve ser sempre do MAIS GRAVE para o MENOS GRAVE.
        val rules = listOf(
            EmergencyRule(),   // 1. Verifica se é Emergência (Vermelho)
            VeryUrgentRule(),  // 2. Se não, verifica Muito Urgente (Laranja)
            UrgentRule()       // 3. Se não, verifica Urgente (Amarelo)
        )

        return PerformTriageService(saveTriagePort, rules, sendTriageEventPort)
    }
}