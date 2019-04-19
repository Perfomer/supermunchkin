package com.volkovmedia.component.coredata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.volkovmedia.component.coredata.converter.MunchkinClassConverter
import com.volkovmedia.component.coredata.converter.MunchkinRaceConverter
import com.volkovmedia.component.coredata.converter.DateConverter
import com.volkovmedia.component.coredata.converter.MunchkinGenderConverter
import com.volkovmedia.component.coredata.dao.MunchkinDao
import com.volkovmedia.component.coredata.dao.TeamDao
import com.volkovmedia.component.coredata.model.entity.Munchkin
import com.volkovmedia.component.coredata.model.entity.Team

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