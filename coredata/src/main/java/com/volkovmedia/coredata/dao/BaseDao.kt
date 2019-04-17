package com.volkovmedia.coredata.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun put(item: T)

    @Delete
    fun remove(item: T)

}