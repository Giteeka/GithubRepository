package com.hkuaapp.data.local.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hkuaapp.modal.evnetdetail.Question
import com.hkuaapp.modal.evnetdetail.QuestionOption

class OptionConverter {
    @TypeConverter
    fun fromOptionList(value: List<QuestionOption>?): String {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<QuestionOption>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toOptionList(value: String): ArrayList<QuestionOption>? {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<QuestionOption>>() {}.type
        return gson.fromJson(value, type)
    }

}