package greachconf.ui.pages

import geb.Page

class HomePage extends Page {

    static url = "/"

    static at = { title.contains 'Greach' }
}
