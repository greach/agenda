package com.greachconf

import geb.Page

class TalkPage extends Page {

    static url = "/talk"

    String convertToPath(Object[] args) {
        args ? '/' + args*.toString().join('/') + '/index.html' : ""
    }

    static content = {
        talkTitle { $('article h1') }
    }

    String getTitle() {
        talkTitle.text()
    }
}
