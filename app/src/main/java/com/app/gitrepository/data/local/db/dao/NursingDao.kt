package com.hkuaapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hkuaapp.modal.home.Document
import com.hkuaapp.modal.nursing.NursingInfo
import io.reactivex.Single

@Dao
interface NursingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNursing(nursingInfo: NursingInfo)

    @Query("SELECT * FROM nursing_info WHERE category_id  = :id")
    fun fetchNursing(id : Int) : Single<NursingInfo>
}