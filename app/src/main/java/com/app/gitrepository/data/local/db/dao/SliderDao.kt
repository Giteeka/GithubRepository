package com.hkuaapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hkuaapp.modal.home.Slider
import io.reactivex.Single

@Dao
interface SliderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSliderList(sliderList: List<Slider>)

    @Query("SELECT * FROM slider")
    fun fetchSliders() : Single<List<Slider>>
}