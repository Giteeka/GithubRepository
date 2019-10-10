package com.app.gitrepository.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Repository {

    @PrimaryKey(autoGenerate = true)
    var id : Long? = null
    var author: String? = null
    var name: String? = null
    var avatar: String? = null
    var url: String? = null
    var description: String? = null
    var language: String? = null
    var languageColor: String? = null
    var stars: Int? = null
    var forks: Int? = null
    var currentPeriodStars: Int? = null
    var builtBy: ArrayList<BuiltBy>? = null

}
