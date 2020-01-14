package com.greachconf

class ErrorSpec extends GebSpecification {

    void "Diversity link is not present at Error Page"() {
        when:
        ErrorPage page = browser.to ErrorPage

        then:
        !page.mainNav.existsMenu('Diversity')
    }

    void "CFP link is not present at Error Page"() {
        when:
        ErrorPage page = browser.to ErrorPage

        then:
        !page.mainNav.existsMenu('CFP')
    }

    void "Link are present at Error Page"() {
        when:
        ErrorPage page = browser.to ErrorPage

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
