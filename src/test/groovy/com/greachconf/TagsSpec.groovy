package com.greachconf


import greachconf.repositories.TalkRepository

class TagsSpec extends GebSpecification {


    TalkRepository talkRepository = embeddedServer.applicationContext.getBean(TalkRepository)

    void "it is possible to navigate to every tag page"() {
        expect:
        browser.to(TagsPage)
        browser.report('tags')

        Map<String, Integer> tagDensitiy = talkRepository.calculateTagDensity()
        for (String tag : tagDensitiy.keySet()) {
            TagPage tagPage = browser.to(TagPage, tag)
            assert browser.driver.pageSource.contains('<a href="/talks/tags/index.html">')
            browser.report(tag)

        }
    }

}
