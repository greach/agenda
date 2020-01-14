package com.greachconf

trait ConfigurationFixture {
    Map<String, Object> getConfiguration() {
        Map<String, Object> m = [:]
        if (specName != null) {
            m['spec.name'] = specName
        }
        m
    }
    String getSpecName() {
        null
    }
}
