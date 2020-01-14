package com.greachconf

import geb.Page

class TicketsPage extends Page {

    static url = '/tickets/index.html'

    static at = { title.contains('Tickets | Micronaut Conference | Greach 2020 - 26th, 27th, 28th March') }

    static content = {
        mainNav { $('nav.wrapper ').module(MainNavigation) }
    }
}
