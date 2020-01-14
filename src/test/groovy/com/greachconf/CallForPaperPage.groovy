package com.greachconf

import geb.Page

class CallForPaperPage extends Page {

    static url = '/cfp/index.html'

    static at = { title.contains('Call for Paper | Micronaut Conference | Greach 2020 - 26th, 27th, 28th March') }

    static content = {
        mainNav { $('nav.wrapper ').module(MainNavigation) }
    }
}
