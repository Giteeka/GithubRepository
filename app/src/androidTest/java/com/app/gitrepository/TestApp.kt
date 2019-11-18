package com.app.gitrepository

class TestApp : GitApp(){
    // set local url for mock web browser
    override val baseUrl: String
        get() = "http://127.0.0.1:8080"

}