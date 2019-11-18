package com.app.gitrepository

class TestApp : GitApp(){
    override val baseUrl: String
        get() = "http://127.0.0.1:8080"

}