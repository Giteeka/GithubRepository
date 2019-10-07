package com.hkuaapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hkuaapp.modal.login.UserDetail
import io.reactivex.Single

@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userDetail: UserDetail)

    @Query("SELECT * FROM user")
    fun fechUser() : Single<UserDetail>
}