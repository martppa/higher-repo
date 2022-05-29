package com.madapp.higherrepo.data.exception

import com.madapp.higherrepo.data.model.RequestError
import java.io.IOException

class RequestException(
        requestError: RequestError
) : IOException(requestError.code)