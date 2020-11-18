package com.hi.common.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author : wbxuzhen
 * @date : 2020/11/10 17:03
 * @description :
 */
@Entity(tableName = "StudentDataBase")
data class StudentEntity(
    //主键自增
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String = ""
)