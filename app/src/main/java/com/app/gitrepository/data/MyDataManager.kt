package com.app.gitrepository.data

import android.content.Context
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.app.gitrepository.SeedDatabaseWorker
import com.app.gitrepository.data.local.*
import com.app.gitrepository.data.model.Repository
import com.app.gitrepository.data.network.ApiHelper
import com.app.gitrepository.data.network.ApiService
import com.app.gitrepository.data.network.MyApiHelper
import com.app.gitrepository.utils.Logg
import com.app.gitrepository.utils.NetworkUtils
import io.reactivex.Observer
import io.reactivex.Single
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 *
 * Created by admin on 9/7/2019.
 */

class MyDataManager(context: Context) : DataManager {

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
        startWorker()

    }

    fun WorkManager.isAnyWorkScheduled(tag: String): Boolean {
        return try {
            getWorkInfosByTag(tag).get().firstOrNull { !it.state.isFinished } != null
        } catch (e: Exception) {
            when (e) {
                is ExecutionException, is InterruptedException -> {
                    e.printStackTrace()
                }
                else -> throw e
            }
            false
        }
    }

    val TAG_PERIODIC_WORK_REQUEST = "jobTag"
    val TAG = "MyDataManager"
    override fun startWorker() {

        if (workManager.isAnyWorkScheduled(TAG_PERIODIC_WORK_REQUEST)) {
            val periodicWorkRequest =
                PeriodicWorkRequest.Builder(
                    SeedDatabaseWorker::class.java,
                    2,
                    TimeUnit.HOURS
                )
                    .addTag(TAG_PERIODIC_WORK_REQUEST)
                    .build()

            // Queue the work
            Logg.e(TAG, "Periodic  Queue the work")
            workManager.enqueue(periodicWorkRequest)
        } else {
            Logg.e(TAG, "Periodic Work already exists")
        }
    }


    override fun getRowItems(): Single<List<Repository>> {
        return dbHelper.getRowItems()
    }

    override fun deleteAll() {
        dbHelper.deleteAll()
    }

    var dbHelper: DbHelper = MyDbHelper(RoomDatabaseHelper.getInstance(context))
    var sharePref: SharedPreferenceHelper
    var apiHelper: ApiHelper
    var networkUtils: NetworkUtils
    var workManager: WorkManager


    init {
        sharePref = SharedPreferenceHelper.getInstance(context)
        apiHelper = MyApiHelper(ApiService.create())
        networkUtils = NetworkUtils(context)
        workManager = WorkManager.getInstance(context)
    }


}