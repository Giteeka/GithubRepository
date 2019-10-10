package com.app.gitrepository.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.gitrepository.data.model.BuiltBy
import com.app.gitrepository.data.model.Repository

@Database(entities = [Repository::class], version = 1)
@TypeConverters(BuiltByConverter::class)
abstract class RoomDatabaseHelper : RoomDatabase() {

    abstract fun rowDao(): RepositoryDao

    companion object {

        fun getInstance(context: Context): RoomDatabaseHelper {
            return Room.databaseBuilder(context, RoomDatabaseHelper::class.java, "Repository-database.db")
                .fallbackToDestructiveMigration()
                .build()
        }

    }
}