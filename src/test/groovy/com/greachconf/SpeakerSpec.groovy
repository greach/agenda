package com.greachconf

import greachconf.repositories.AgendaRepository
import greachconf.vm.Agenda
import greachconf.vm.AgendaDay
import greachconf.vm.AgendaTalk
import greachconf.vm.AgendaTalkSpeaker
import greachconf.vm.AgendaTimeSlot

class SpeakerSpec extends GebSpecification {

    AgendaRepository agendaRepository = applicationContext.getBean(AgendaRepository)

    void "it is possible to navigate to every page"() {
        expect:
        Agenda agenda = agendaRepository.findAgenda()
        for (AgendaDay agendaDay : agenda.days) {
            for (AgendaTimeSlot agendaTimeSlot : agendaDay.timeSlots) {
                for (AgendaTalk talk : agendaTimeSlot.trackTalks.values()) {

                    for (AgendaTalkSpeaker agendaTalkSpeaker : talk.getSpeakers()) {
                        SpeakerPage speakerPage = browser.to(SpeakerPage, agendaTalkSpeaker.uid)
                        assert speakerPage.title == agendaTalkSpeaker.name
                        browser.report(agendaTalkSpeaker.uid)
                    }

                }
            }

        }
    }
}
