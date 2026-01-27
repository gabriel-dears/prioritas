package br.com.fiap.adj8.phase5.prioritas.infra.config

import br.com.fiap.adj8.phase5.prioritas.application.port.`in`.PerformTriageUseCase
import br.com.fiap.adj8.phase5.prioritas.domain.model.VitalSigns
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.util.UUID

@Configuration
@Profile("!test") // Não roda durante os testes automatizados para não sujar o banco
class DataSeeder(
    private val performTriageUseCase: PerformTriageUseCase
) : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(DataSeeder::class.java)

    override fun run(vararg args: String?) {
        logger.info("🌱 Iniciando Data Seeding (Populando banco com dados fake)...")

        try {
            // Cenário 1: Paciente com Infarto (EMERGENCY)
            seedTriage(
                description = "Paciente Sr. João (Infarto)",
                vitalSigns = VitalSigns(
                    hasChestPain = true,
                    heartRate = 120,
                    oxygenSaturation = 90,
                    temperature = 36.5,
                    systolicPressure = 160,
                    diastolicPressure = 100
                )
            )

            // Cenário 2: Paciente com Febre Alta (URGENT - Laranja)
            seedTriage(
                description = "Paciente Maria (Febre Alta)",
                vitalSigns = VitalSigns(
                    hasChestPain = false,
                    heartRate = 100,
                    oxygenSaturation = 96,
                    temperature = 39.5, // Febre alta
                    systolicPressure = 120,
                    diastolicPressure = 80
                )
            )

            // Cenário 3: Checkup Normal (NORMAL - Azul/Verde)
            seedTriage(
                description = "Paciente Pedro (Checkup)",
                vitalSigns = VitalSigns(
                    hasChestPain = false,
                    heartRate = 70,
                    oxygenSaturation = 99,
                    temperature = 36.5,
                    systolicPressure = 120,
                    diastolicPressure = 80
                )
            )

            logger.info("✅ Data Seeding concluído com sucesso!")

        } catch (e: Exception) {
            logger.error("❌ Erro ao rodar Data Seeding: ${e.message}")
        }
    }

    private fun seedTriage(description: String, vitalSigns: VitalSigns) {
        val patientId = UUID.randomUUID()
        logger.info("Processing -> $description | ID: $patientId")
        performTriageUseCase.execute(patientId, vitalSigns)
    }
}