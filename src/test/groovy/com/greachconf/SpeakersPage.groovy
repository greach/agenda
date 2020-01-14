package com.greachconf

import geb.Page

class SpeakersPage extends Page {

    static url = '/speakers/index.html'

    static at = { title.contains('Speakers | Micronaut Conference | Greach 2020 - 26th, 27th, 28th March') }

    static content = {
        mainNav { $('nav.wrapper ').module(MainNavigation) }
    }
}
