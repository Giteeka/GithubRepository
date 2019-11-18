package com.app.gitrepository

import android.app.Application

open class GitApp : Application() {
    open val baseUrl: String
        get() = "https://github-trending-api.now.sh/"

}