package com.app.gitrepository.data.local

import androidx.room.TypeConverter
import com.app.gitrepository.data.model.BuiltBy
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BuiltByConverter {
    @TypeConverter
    fun fromCategoryList(value: List<BuiltBy>?): String {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<BuiltBy>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCategoryList(value: String): ArrayList<BuiltBy>? {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<BuiltBy>>() {}.type
        return gson.fromJson(value, type)
    }

}