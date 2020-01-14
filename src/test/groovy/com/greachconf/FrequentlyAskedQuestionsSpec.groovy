package com.greachconf

class FrequentlyAskedQuestionsSpec extends GebSpecification {

    void "CFP link is not present at FAQ page"() {
        when:
        FrequentlyAskedQuestionsPage page = browser.to FrequentlyAskedQuestionsPage

        then:
        !page.mainNav.existsMenu('CFP')
    }

    void "Diversity link is not present at FAQ page"() {
        when:
        FrequentlyAskedQuestionsPage page = browser.to FrequentlyAskedQuestionsPage

        then:
        !page.mainNav.existsMenu('Diversity')
    }

    void "links are not present at FAQ page"() {
        when:
        FrequentlyAskedQuestionsPage page = browser.to FrequentlyAskedQuestionsPage

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
