package com.hi.common.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hi.common.room.entity.StudentEntity

/**
 * @author : wbxuzhen
 * @date : 2020/11/10 17:06
 * @description :
 */
@Dao
interface StudentDao {

    @Insert
    fun insert(studentEntity: List<StudentEntity>)

    @Query("SELECT * FROM StudentDataBase")
    fun loadAllUser(): LiveData<List<StudentEntity>>

    @Query("DELETE FROM StudentDataBase WHERE id=:id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM StudentDataBase")
    fun deleteAll()
}