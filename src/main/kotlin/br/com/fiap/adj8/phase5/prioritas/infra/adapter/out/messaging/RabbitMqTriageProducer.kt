package br.com.fiap.adj8.phase5.prioritas.infra.adapter.out.messaging

import br.com.fiap.adj8.phase5.prioritas.application.port.out.SendTriageEventPort
import br.com.fiap.adj8.phase5.prioritas.common.event.TRIAGE_QUEUE
import br.com.fiap.adj8.phase5.prioritas.common.event.TriageNotificationEvent
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class RabbitMqTriageProducer(
    private val rabbitTemplate: RabbitTemplate
) : SendTriageEventPort {

    private val logger = LoggerFactory.getLogger(RabbitMqTriageProducer::class.java)

    override fun send(event: TriageNotificationEvent) {
        try {
            rabbitTemplate.convertAndSend(TRIAGE_QUEUE, event)
        } catch (e: Exception) {
            logger.error("❌ Erro ao enviar mensagem para o RabbitMQ", e)
        }
    }
}