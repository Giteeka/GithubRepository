package com.app.gitrepository.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.gitrepository.data.model.Repository
import io.reactivex.Single

interface DbHelper{

    fun insert(item: Repository)

    fun insert(item: List<Repository>)

    fun getRowItems(): Single<List<Repository>>

    fun deleteAll()

}