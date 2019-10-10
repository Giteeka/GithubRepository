package com.app.gitrepository.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.gitrepository.data.model.Repository
import io.reactivex.Single

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Repository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<Repository>)

    @Query("SELECT * from Repository")
    fun getRowItems(): Single<List<Repository>>

    @Query("DELETE from Repository")
    fun deleteAll()

}