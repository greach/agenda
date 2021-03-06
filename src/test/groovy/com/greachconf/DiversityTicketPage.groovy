package com.greachconf

import geb.Page

class DiversityTicketPage extends Page {
    static url = '/diversity/index.html'

    static at = { title.contains('Diversity | Micronaut Conference | Greach 2020 - 26th, 27th, 28th March') }

    static content = {
        mainNav { $('nav.wrapper ').module(MainNavigation) }
    }
}
