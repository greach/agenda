package com.greachconf

class TicketsSpec extends GebSpecification {

    void "CFP link is not present at Tickets Page"() {
        when:
        TicketsPage page = browser.to TicketsPage

        then:
        !page.mainNav.existsMenu('CFP')
    }

    void "Diversity link is not present at Tickets Page"() {
        when:
        TicketsPage page = browser.to TicketsPage

        then:
        !page.mainNav.existsMenu('Diversity')
    }

    void "Links are present at Tickets Page"() {
        when:
        TicketsPage page = browser.to TicketsPage

        then:
        page.mainNav.existsMenu('Speakers')
        page.mainNav.existsMenu('Agenda')
        page.mainNav.existsMenu('Venue')
        page.mainNav.existsMenu('Tickets')
        page.mainNav.existsMenu('FAQ')
        page.mainNav.existsMenu('Sponsors')
        page.mainNav.existsMenu('COC')
    }
}
