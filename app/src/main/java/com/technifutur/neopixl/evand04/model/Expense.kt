package com.technifutur.neopixl.evand04.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "expense_id")
    val expenseId: Long = 0,
    val name: String,
    val date: String,
    val value: Float,
    @ColumnInfo(name = "type_id")
    val idType: Long


)

