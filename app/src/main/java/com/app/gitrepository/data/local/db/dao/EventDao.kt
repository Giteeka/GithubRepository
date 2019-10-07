package com.hkuaapp.data.local.db.dao

import androidx.room.*
import com.hkuaapp.modal.events.EventInfo
import com.hkuaapp.modal.login.UserDetail
import io.reactivex.Single

@Dao
interface EventDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEventList(eventList: List<EventInfo>)

    @Query("SELECT * FROM event")
    fun fetchEvent() : Single<List<EventInfo>>

  /*  @Query("SELECT * FROM event WHERE category_id = :categoryId  AND  event_type = :eventType")*/

    @Query("SELECT * FROM event WHERE (category_id = :categoryId OR category_id = 0) AND  event_type = :eventType")
    fun fetchEvent(categoryId : Int,eventType : Int) : Single<List<EventInfo>>

    @Update
    fun updateEvent(evnetInfo: EventInfo)

    @Query("SELECT  * FROM event WHERE id = :eventId")
    fun getEventById(eventId : Int) : Single<EventInfo>
}


