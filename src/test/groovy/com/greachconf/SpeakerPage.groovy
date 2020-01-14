package com.greachconf

import geb.Page

class SpeakerPage extends Page {

    static url = '/speaker'

    String convertToPath(Object[] args) {
        args ? '/' + args*.toString().join('/') + '/index.html' : ""
    }

    static content = {
        speakerTitle { $('article h1') }
    }

    String getTitle() {
        speakerTitle.text()
    }
}
