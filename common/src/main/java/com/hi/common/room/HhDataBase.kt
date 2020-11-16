package com.hi.common.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hi.common.room.dao.StudentDao
import com.hi.common.room.entity.StudentEntity

/**
 * @author : wbxuzhen
 * @date : 2020/11/10 16:49
 * @description :
 */
@Database(entities = [StudentEntity::class], version = 1)
abstract class HhDataBase : RoomDatabase() {
    companion object {
        @Synchronized
        fun getDataBase(context: Context): HhDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                HhDataBase::class.java,
                "hh_database"
            ).build()
        }
    }

    abstract fun studentDao(): StudentDao
}