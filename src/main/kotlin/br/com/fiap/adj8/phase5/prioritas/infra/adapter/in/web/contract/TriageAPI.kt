package br.com.fiap.adj8.phase5.prioritas.infra.adapter.`in`.web.contract

import br.com.fiap.adj8.phase5.prioritas.infra.adapter.`in`.web.dto.TriageRequest
import br.com.fiap.adj8.phase5.prioritas.infra.adapter.`in`.web.dto.TriageResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(name = "Triagem", description = "Endpoints para realização de triagem de pacientes")
interface TriageAPI {

    @Operation(
        summary = "Realizar nova triagem",
        description = "Calcula o nível de risco baseado nos sinais vitais do paciente."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Triagem realizada com sucesso"),
            ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            ApiResponse(responseCode = "500", description = "Erro interno no servidor")
        ]
    )
    fun performTriage(
        @RequestBody(description = "Dados vitais para análise", required = true)
        request: TriageRequest
    ): ResponseEntity<TriageResponse>
}