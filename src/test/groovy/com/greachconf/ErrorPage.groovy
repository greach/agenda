package com.greachconf

import geb.Page

class ErrorPage extends Page {

    static url = '/error.html'

    static at = { title.contains('Error | Micronaut Conference | Greach 2020 - 26th, 27th, 28th March') }

    static content = {
        mainNav { $('nav.wrapper ').module(MainNavigation) }
    }
}
