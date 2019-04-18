package com.volkovmedia.coredata.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(item: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T)

    @Delete
    fun remove(item: T)

}