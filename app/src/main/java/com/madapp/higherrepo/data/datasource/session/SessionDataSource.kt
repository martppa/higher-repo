package com.madapp.higherrepo.data.datasource.session

import com.madapp.higherrepo.data.model.Session

interface SessionDataSource {
    fun getSession(): Session
}