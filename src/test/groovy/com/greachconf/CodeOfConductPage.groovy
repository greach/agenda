package com.greachconf

import geb.Page

class CodeOfConductPage extends Page {

    static url = '/coc/index.html'

    static at = { title.contains('Code of Conduct | Micronaut Conference | Greach 2020 - 26th, 27th, 28th March') }

    static content = {
        mainNav { $('nav.wrapper ').module(MainNavigation) }
    }
}
