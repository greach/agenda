package com.greachconf

class AgendaSpec extends GebSpecification {

    void "Diversity link is not present at Agenda Page"() {
        when:
        AgendaPage page = browser.to AgendaPage

        then:
        !page.mainNav.existsMenu('Diversity')
    }

    void "CFP link is not present at Agenda Page"() {
        when:
        AgendaPage page = browser.to AgendaPage

        then:
        !page.mainNav.existsMenu('CFP')
    }

    void "Links are present at Agenda Page"() {
        when:
        AgendaPage page = browser.to AgendaPage

        then:
        page.mainNav.existsMenu('Speakers')
        page.mainNav.existsMenu('Agenda')
        page.mainNav.existsMenu('Venue')
        page.mainNav.existsMenu('Tickets')
        page.mainNav.existsMenu('FAQ')
        page.mainNav.existsMenu('Sponsors')
        page.mainNav.existsMenu('COC')

        report('index')
    }
}
