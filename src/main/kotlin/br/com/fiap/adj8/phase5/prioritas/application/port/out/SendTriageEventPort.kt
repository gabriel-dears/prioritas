package br.com.fiap.adj8.phase5.prioritas.application.port.out

import br.com.fiap.adj8.phase5.prioritas.common.event.TriageNotificationEvent

// TODO:
// verify not only EMERGENCY SENT
// create notification-app
interface SendTriageEventPort {
    fun send(event: TriageNotificationEvent)
}