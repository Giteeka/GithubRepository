package com.app.gitrepository.data

import com.app.gitrepository.data.local.DbHelper
import com.app.gitrepository.data.model.Repository
import com.app.gitrepository.data.network.ApiHelper
import io.reactivex.Single

interface DataManager : ApiHelper,DbHelper{
    fun isNetworkConnected() : Boolean
    fun startWorker()
}