package com.greachconf

import geb.Module

class MainNavigation extends Module {

    boolean existsMenu(String linkText) {
        !$('a', text: linkText).isEmpty()
    }
}
