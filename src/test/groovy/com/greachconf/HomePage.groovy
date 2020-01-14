package com.greachconf

import geb.Page

class HomePage extends Page {

    static url = '/index.html'

    static at = { title.contains('Greach Conference 2020 - 26th, 27th, 28th March') }

    static content = {
        mainNav { $('nav.wrapper ').module(MainNavigation) }
    }
}
