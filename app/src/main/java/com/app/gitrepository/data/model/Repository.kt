package com.app.gitrepository.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(ignoredColumns = ["isExpanded"])
data class Repository(
    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0,
    var author: String?,
    var name: String?,
    var avatar: String?,
    var url: String?,
    var description: String?,
    var language: String?,
    var languageColor: String?,
    var stars: String?,
    var forks: String?,
    var currentPeriodStars: String?,
    var builtBy: ArrayList<BuiltBy?>?
)
{
    var isExpanded : Boolean? = false

}

