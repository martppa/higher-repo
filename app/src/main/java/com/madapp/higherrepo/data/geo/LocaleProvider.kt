package com.madapp.higherrepo.data.geo

import java.util.Locale

class LocaleProvider {
    fun getSystemLanguage(): String {
        return Locale.getDefault().language
    }
}