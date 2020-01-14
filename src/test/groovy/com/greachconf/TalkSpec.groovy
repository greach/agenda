package com.greachconf

import greachconf.repositories.AgendaRepository
import greachconf.vm.Agenda
import greachconf.vm.AgendaDay
import greachconf.vm.AgendaTalk
import greachconf.vm.AgendaTimeSlot

class TalkSpec extends GebSpecification {

    AgendaRepository agendaRepository = applicationContext.getBean(AgendaRepository)

    void "it is possible to navigate to every page"() {
        expect:
        Agenda agenda = agendaRepository.findAgenda()
        for (AgendaDay agendaDay : agenda.days) {
            for (AgendaTimeSlot agendaTimeSlot : agendaDay.timeSlots) {
                for (AgendaTalk talk : agendaTimeSlot.trackTalks.values()) {
                    TalkPage talkPage = browser.to(TalkPage, talk.uid)
                    assert talkPage.title == talk.title
                    browser.report(talk.uid)
                }
            }

        }
    }
}
