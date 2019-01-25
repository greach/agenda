package greachconf.ui.pages

import geb.Page

class SpeakerPage extends Page {

    static url = "/speaker"

    static at = { title.contains('Speaker | Greach 2019')}

    @Override
    String convertToPath(Object[] args) {
        args ? '/' + args*.toString().join('/') : ""
    }

    static content = {
        nameHeader { $('article h1') }
    }

    String getName() {
        nameHeader.text()
    }
}
