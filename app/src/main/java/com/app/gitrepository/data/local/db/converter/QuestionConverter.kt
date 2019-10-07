package com.hkuaapp.data.local.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hkuaapp.modal.evnetdetail.Question

class QuestionConverter {
    @TypeConverter
    fun fromQuestionList(value: List<Question>?): String {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Question>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toQuestionList(value: String): ArrayList<Question>? {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Question>>() {}.type
        return gson.fromJson(value, type)
    }

}