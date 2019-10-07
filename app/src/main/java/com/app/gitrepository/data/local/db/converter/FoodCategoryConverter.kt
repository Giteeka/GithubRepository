package com.hkuaapp.data.local.db.converter

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hkuaapp.modal.events.FoodCategory

class FoodCategoryConverter {
    @TypeConverter
    fun fromCategoryList(value: List<FoodCategory>?): String {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<FoodCategory>>() {}.type
        Log.e("category",""+gson.toJson(value, type))
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCategoryList(value: String): ArrayList<FoodCategory>? {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<FoodCategory>>() {}.type
        return gson.fromJson(value, type)
    }

}