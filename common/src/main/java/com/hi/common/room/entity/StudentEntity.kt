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
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "age")
    var age: Int,
    @ColumnInfo(name = "name")
    var name: String
)