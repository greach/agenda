package com.greachconf

import geb.Page

class FrequentlyAskedQuestionsPage extends Page {

    static url = '/faq/index.html'

    static at = { title.contains('Frequently Asked Questions | Micronaut Conference | Greach 2020 - 26th, 27th, 28th March') }

    static content = {
        mainNav { $('nav.wrapper ').module(MainNavigation) }
    }
}
