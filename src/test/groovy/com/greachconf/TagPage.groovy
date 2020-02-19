package com.greachconf

import geb.Page

class TagPage extends Page {

    static url = "/talks/tags"

    @Override
    String convertToPath(Object[] args) {
        args ? '/' + args*.toString().join('/') + '/index.html' : ""
    }
}
