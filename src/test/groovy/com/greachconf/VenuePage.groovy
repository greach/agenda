package com.greachconf

import geb.Page

class VenuePage extends Page {

    static url = '/venue/index.html'

    static at = { title.contains('Venue | Micronaut Conference | Greach 2020 - 26th, 27th, 28th March') }

    static content = {
        mainNav { $('nav.wrapper ').module(MainNavigation) }
    }
}
