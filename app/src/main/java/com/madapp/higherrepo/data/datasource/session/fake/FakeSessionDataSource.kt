package com.madapp.higherrepo.data.datasource.session.fake

import com.madapp.higherrepo.data.datasource.session.SessionDataSource
import com.madapp.higherrepo.data.model.Session

class FakeSessionDataSource : SessionDataSource {
    override fun getSession(): Session {
        return Session("FakeToken", "FakeRefresh")
    }
}