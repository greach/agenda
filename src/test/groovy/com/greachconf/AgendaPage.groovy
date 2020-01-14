package com.greachconf

import geb.Page

class AgendaPage extends Page {

    static url = '/agenda/index.html'

    static at = { title.contains('Agenda | Micronaut Conference | Greach 2020 - 26th, 27th, 28th March') }

    static content = {
        mainNav { $('nav.wrapper ').module(MainNavigation) }
    }
}
