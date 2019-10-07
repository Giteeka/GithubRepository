package com.hkuaapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hkuaapp.modal.home.Document
import io.reactivex.Single

@Dao
interface DocumentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDocumentList(docList: List<Document>)

    @Query("SELECT * FROM document")
    fun fetchDocumentList() : Single<List<Document>>

    @Query("SELECT * FROM document WHERE category_id = :id")
    fun fetchDocumentList(id : Int) : Single<List<Document>>

}