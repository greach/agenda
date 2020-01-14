package com.greachconf

class SponsorsSpec extends GebSpecification {

    void "CFP link is not present at Sponsors page"() {
        when:
        SponsorsPage page = browser.to SponsorsPage

        then:
        !page.mainNav.existsMenu('CFP')
    }

    void "Diversity link is not present at Sponsors page"() {
        when:
        SponsorsPage page = browser.to SponsorsPage

        then:
        !page.mainNav.existsMenu('Diversity')
    }

    void "links are present at Sponsors page"() {
        when:
        SponsorsPage page = browser.to SponsorsPage

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
