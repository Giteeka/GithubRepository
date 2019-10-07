package com.hkuaapp.data.local.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hkuaapp.modal.evnetdetail.Question
import com.hkuaapp.modal.nursing.Table

class TableConverter {
    @TypeConverter
    fun fromTableList(value: List<Table>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<Table>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toTableList(value: String) : List<Table>? {
        val gson = Gson()
        val type = object : TypeToken<List<Table>>() {}.type
        return gson.fromJson(value, type)
    }

}