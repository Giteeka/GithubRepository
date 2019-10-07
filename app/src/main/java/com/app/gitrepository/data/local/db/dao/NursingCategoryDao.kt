package com.hkuaapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hkuaapp.modal.home.Document
import com.hkuaapp.modal.nursing.NursingCategory
import io.reactivex.Single

@Dao
interface NursingCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategoryList(catList: List<NursingCategory>)

    @Query("SELECT * FROM nursing_category")
    fun fetchNursingCategoryList() : Single<List<NursingCategory>>
}