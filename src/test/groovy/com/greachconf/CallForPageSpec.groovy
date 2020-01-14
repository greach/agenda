package com.greachconf

class CallForPageSpec extends GebSpecification {

    void "CFP link is not present at CFP page"() {
        when:
        CallForPaperPage page = browser.to CallForPaperPage

        then:
        !page.mainNav.existsMenu('CFP')
    }

    void "Diversity link is not present at CFP page"() {
        when:
        CallForPaperPage page = browser.to CallForPaperPage

        then:
        !page.mainNav.existsMenu('Diversity')
    }

    void "Links are not present at CFP page"() {
        when:
        CallForPaperPage page = browser.to CallForPaperPage

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
