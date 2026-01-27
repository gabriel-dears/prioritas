package br.com.fiap.adj8.phase5.prioritas.integration

import br.com.fiap.adj8.phase5.prioritas.AbstractIntegrationTest
import br.com.fiap.adj8.phase5.prioritas.domain.model.RiskLevel
import br.com.fiap.adj8.phase5.prioritas.infra.adapter.out.persistence.SpringDataTriageRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.UUID

@AutoConfigureMockMvc
class TriageIntegrationTest : AbstractIntegrationTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var repository: SpringDataTriageRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `should perform triage and save result as EMERGENCY when symptoms match`() {
        // 1. Arrange: Criar o JSON de Request (Paciente com dor no peito)
        val patientId = UUID.randomUUID()
        val requestBody = mapOf(
            "patientId" to patientId,
            "hasChestPain" to true,
            "heartRate" to 110,
            "oxygenSaturation" to 98
        )

        // 2. Act: Fazer o POST na API
        mockMvc.perform(
            post("/api/triages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            // 3. Assert (HTTP): Verificar se retornou 201 Created e o JSON correto
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.riskLevel").value("EMERGENCY")) // Verifica o Enum
            .andExpect(jsonPath("$.riskColor").value("Red"))       // Verifica a cor
            .andExpect(jsonPath("$.waitTimeMinutes").value(0))     // Verifica o tempo

        // 4. Assert (Database): Verificar se salvou de verdade no banco
        val savedTriages = repository.findAll()
        assertThat(savedTriages).hasSize(1)

        val triage = savedTriages[0]
        assertThat(triage.patientId).isEqualTo(patientId)
        assertThat(triage.riskLevel).isEqualTo(RiskLevel.EMERGENCY)
        assertThat(triage.hasChestPain).isTrue()
    }
}