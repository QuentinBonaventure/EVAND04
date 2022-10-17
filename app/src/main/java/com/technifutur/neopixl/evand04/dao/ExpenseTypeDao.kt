package com.technifutur.neopixl.evand04.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.technifutur.neopixl.evand04.model.ExpenseType


@Dao
interface ExpenseTypeDao {

    @Query("SELECT * FROM ExpenseType")
    fun getAll(): LiveData<List<ExpenseType>>

    @Query("SELECT * FROM ExpenseType WHERE expense_type_id = :expenseTypeId LIMIT 1")
    fun loadById(expenseTypeId: Long): LiveData<ExpenseType>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(expenseType: ExpenseType)

    @Query("SELECT * FROM expensetype")
    suspend fun getAllExpenseTypes(): List<ExpenseType>

}