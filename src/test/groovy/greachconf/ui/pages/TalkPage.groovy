package greachconf.ui.pages

import geb.Page

class TalkPage extends Page {

    static url = "/talk"

    static at = { title.contains('| Talk | Greach 2019') }

    @Override
    String convertToPath(Object[] args) {
        args ? '/' + args*.toString().join('/') : ""
    }

    static content = {
        titleHeader { $('article h1') }
    }

    String getTalkTitle() {
        titleHeader.text()
    }
}
