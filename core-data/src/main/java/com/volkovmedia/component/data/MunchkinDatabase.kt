package com.volkovmedia.component.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.volkovmedia.component.data.converter.MunchkinClassConverter
import com.volkovmedia.component.data.converter.MunchkinRaceConverter
import com.volkovmedia.component.data.converter.DateConverter
import com.volkovmedia.component.data.converter.MunchkinGenderConverter
import com.volkovmedia.component.data.dao.MunchkinDao
import com.volkovmedia.component.data.dao.TeamDao
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.component.data.model.entity.Team

@Database(
    version = 3,
    exportSchema = true,
    entities = [
        Munchkin::class,
        Team::class
    ]
)
@TypeConverters(
    MunchkinClassConverter::class,
    MunchkinRaceConverter::class,
    MunchkinGenderConverter::class,
    DateConverter::class
)
abstract class MunchkinDatabase : RoomDatabase() {

    abstract fun getTeamDao(): TeamDao

    abstract fun getMunchkinDao(): MunchkinDao

    companion object {

        @Synchronized
        fun getInstance(
            appContext: Context,
            databaseName: String,
            inMemory: Boolean = false
        ): MunchkinDatabase {
            return if (inMemory) {
                Room.inMemoryDatabaseBuilder(appContext, MunchkinDatabase::class.java).build()
            } else {
                Room.databaseBuilder(appContext, MunchkinDatabase::class.java, databaseName)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }

    }

}