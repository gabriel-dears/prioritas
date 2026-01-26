package br.com.fiap.adj8.phase5.prioritas.infra.adapter.`in`.web

import br.com.fiap.adj8.phase5.prioritas.application.port.`in`.PerformTriageUseCase
import br.com.fiap.adj8.phase5.prioritas.infra.adapter.`in`.web.dto.TriageRequest
import br.com.fiap.adj8.phase5.prioritas.infra.adapter.`in`.web.dto.TriageResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/triages")
class TriageController(
    private val performTriageUseCase: PerformTriageUseCase
) {

    @PostMapping
    fun performTriage(@RequestBody request: TriageRequest): ResponseEntity<TriageResponse> {
        // 1. Converte DTO -> Domínio
        val vitalSigns = request.toDomain()

        // 2. Chama o Caso de Uso
        val triageResult = performTriageUseCase.execute(request.patientId, vitalSigns)

        // 3. Converte Resultado -> DTO de Resposta
        val response = TriageResponse.fromDomain(triageResult)

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}