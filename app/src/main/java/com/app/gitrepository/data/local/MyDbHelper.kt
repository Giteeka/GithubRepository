package com.app.gitrepository.data.local

import com.app.gitrepository.data.model.Repository
import io.reactivex.Single

open class MyDbHelper(var databaseHelper: RoomDatabaseHelper) : DbHelper {
    override fun getRowByName(name: String): Single<Repository> {
        return databaseHelper.rowDao().getRowItemByName(name)
    }

    override fun insert(item: Repository) {
        databaseHelper.rowDao().insert(item)
    }

    override fun insert(item: List<Repository>) {
        databaseHelper.rowDao().insert(item)
    }

    override fun getRowItems(): Single<List<Repository>> {
        return databaseHelper.rowDao().getRowItems()
    }

    override fun deleteAll() {
        databaseHelper.rowDao().deleteAll()
    }

}