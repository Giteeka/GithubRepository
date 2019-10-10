package com.app.gitrepository.data.network

import com.app.gitrepository.data.model.Repository
import io.reactivex.Single


/**
 *
 * Retrofit client for Retrofit API
 */
interface ApiHelper {

    fun fetchHomeScreenData(): Single<List<Repository>>
}
