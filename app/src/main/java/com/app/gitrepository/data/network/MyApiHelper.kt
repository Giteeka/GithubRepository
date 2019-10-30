package com.app.gitrepository.data.network

import com.app.gitrepository.data.model.Repository
import io.reactivex.Single

open class MyApiHelper(var apiService: ApiService) : ApiHelper {

    override fun fetchHomeScreenData(): Single<List<Repository>> {
        return apiService.getRepositories()
    }

}