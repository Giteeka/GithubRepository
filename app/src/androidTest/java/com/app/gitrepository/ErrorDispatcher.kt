package com.app.gitrepository

import android.net.Uri
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class ErrorDispatcher : Dispatcher(){


    override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse().setResponseCode(404)
    }

}