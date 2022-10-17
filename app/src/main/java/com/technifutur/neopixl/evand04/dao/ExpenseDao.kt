package com.technifutur.neopixl.evand04.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.technifutur.neopixl.evand04.model.Expense
import com.technifutur.neopixl.evand04.model.ExpenseWithExpenseType

@Dao
interface ExpenseDao {

@Query("SELECT * FROM Expense")
fun getAllExpenses(): LiveData<List<Expense>>

@Query("SELECT * FROM Expense WHERE expense_id = :expenseId LIMIT 1")
fun loadById(expenseId: Long): LiveData<Expense>

@Insert(onConflict = OnConflictStrategy.REPLACE)
fun insert(expense: Expense): Long

@Query("SELECT * FROM expense WHERE expense_id = :expenseId LIMIT 1")
fun findExpenseWithExpenseTypeById(expenseId: Long): LiveData<ExpenseWithExpenseType>

@Transaction
@Query("SELECT * FROM Expense")
fun getExpenseAndType(): List<ExpenseWithExpenseType>



}