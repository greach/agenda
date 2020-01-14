package com.greachconf

class VenueSpec extends GebSpecification {

    void "CFP link is not present at Venue Page"() {
        when:
        VenuePage page = browser.to VenuePage

        then:
        !page.mainNav.existsMenu('CFP')
    }

    void "Diversity link is not present at Venue Page"() {
        when:
        VenuePage page = browser.to VenuePage

        then:
        !page.mainNav.existsMenu('Diversity')
    }

    void "Links are present at Venue Page"() {
        when:
        VenuePage page = browser.to VenuePage

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
