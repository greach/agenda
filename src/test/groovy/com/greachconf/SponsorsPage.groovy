package com.greachconf

import geb.Page

class SponsorsPage  extends Page {

    static url = '/sponsors/index.html'

    static at = { title.contains('Sponsors | Micronaut Conference | Greach 2020 - 26th, 27th, 28th March') }

    static content = {
        mainNav { $('nav.wrapper ').module(MainNavigation) }
    }
}
