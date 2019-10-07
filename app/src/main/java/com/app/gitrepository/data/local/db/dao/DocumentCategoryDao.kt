package com.hkuaapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hkuaapp.modal.document.DocumentCategory
import io.reactivex.Single

@Dao
interface DocumentCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDocumentCategoryList(docList: List<DocumentCategory>)

    @Query("SELECT * FROM document_category")
    fun fetchDocumentCategroyList() : Single<List<DocumentCategory>>
}