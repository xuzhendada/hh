package com.hi.common.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hi.common.room.entity.StudentEntity

/**
 * @author : wbxuzhen
 * @date : 2020/11/10 17:06
 * @description :
 */
interface StudentDao {

    @Insert
    suspend fun insert(studentEntity: StudentEntity)

    @Query("SELECT * FROM StudentDataBase")
    suspend fun loadAllUser(): LiveData<List<StudentEntity>>

    @Query("DELETE FROM StudentDataBase WHERE id=:id")
    suspend fun delete(id: Int)

    @Delete
    suspend fun deleteAll()
}