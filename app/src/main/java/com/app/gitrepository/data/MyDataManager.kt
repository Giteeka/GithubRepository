package com.app.gitrepository.data

import android.content.Context
import com.app.gitrepository.data.local.*
import com.app.gitrepository.data.model.Repository
import com.app.gitrepository.data.network.ApiHelper
import com.app.gitrepository.data.network.ApiService
import com.app.gitrepository.data.network.MyApiHelper
import com.app.gitrepository.utils.NetworkUtils
import io.reactivex.Single

/**
 *
 * Created by admin on 9/7/2019.
 */

class MyDataManager(context: Context) : DataManager {
    override fun getDataForHome(): Single<List<Repository>> {
        if (isNetworkConnected())
            return fetchHomeScreenData()
        else
            return getRowItems()

    }

    override fun isNetworkConnected(): Boolean {
        return networkUtils.isNetworkConnected()
    }

    override fun fetchHomeScreenData(): Single<List<Repository>> {
        return apiHelper.fetchHomeScreenData()
    }

    override fun insert(item: Repository) {
        dbHelper.insert(item)
    }

    override fun insert(item: List<Repository>) {
        dbHelper.insert(item)
    }

    override fun getRowItems(): Single<List<Repository>> {
        return dbHelper.getRowItems()
    }

    override fun deleteAll() {
        dbHelper.deleteAll()
    }

    var dbHelper: DbHelper
    var sharePref: SharedPreferenceHelper
    var apiHelper: ApiHelper
    var networkUtils: NetworkUtils


    init {
        dbHelper = MyDbHelper(RoomDatabaseHelper.getInstance(context))
        sharePref = SharedPreferenceHelper.getInstance(context)
        apiHelper = MyApiHelper(ApiService.create())
        networkUtils = NetworkUtils(context)
    }


}