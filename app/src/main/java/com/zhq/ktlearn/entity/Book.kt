package com.zhq.ktlearn.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/11 15:39
 * Description
 */
@Entity(tableName = "book")
data class Book(
    @ColumnInfo(name = "id") @PrimaryKey val id: Int,
    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT) val name: String,
    @ColumnInfo(name = "category", typeAffinity = ColumnInfo.TEXT) val category: String
)
