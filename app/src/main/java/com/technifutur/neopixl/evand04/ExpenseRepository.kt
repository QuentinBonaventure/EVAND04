package com.technifutur.neopixl.evand04

import android.content.Context
import androidx.lifecycle.LiveData
import com.technifutur.neopixl.evand04.db.ExpenseDataBase
import com.technifutur.neopixl.evand04.model.Expense
import com.technifutur.neopixl.evand04.model.ExpenseType
import com.technifutur.neopixl.evand04.model.ExpenseWithExpenseType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ExpenseRepository {

    companion object {
        var expenseDataBase: ExpenseDataBase? = null
        var expense: LiveData<ExpenseWithExpenseType>? = null
        var expenses: LiveData<List<Expense>>? = null


        fun initializeDB(context: Context): ExpenseDataBase {
            val db = ExpenseDataBase.getDB(context)
            CoroutineScope(IO).launch {
                val expenseType = expenseDataBase!!.expenseTypeDao().getAllExpenseTypes()
                if (expenseType.isNullOrEmpty()) {
                    expenseDataBase!!.expenseTypeDao().insert(ExpenseType(name = "Tax"))
                    expenseDataBase!!.expenseTypeDao().insert(ExpenseType(name = "Cars"))
                    expenseDataBase!!.expenseTypeDao().insert(ExpenseType(name = "Foods"))
                    expenseDataBase!!.expenseTypeDao().insert(ExpenseType(name = "Energy"))
                }
            }
            return db

        }

        fun insertExpense(
            context: Context,
            name: String,
            date: String,
            value: Float,
            idType: Long,
           // selectedType: ArrayList<ExpenseType>
        ){
            expenseDataBase = initializeDB(context)
            CoroutineScope(IO).launch {
                val expense = Expense(name = name,date = date, value = value, idType = idType)
                 expenseDataBase!!.expenseDao().insert(expense)


            }

        }

        fun insertType(context: Context,name: String){
            if(expenseDataBase == null){
                expenseDataBase = initializeDB(context)
            }
            CoroutineScope(IO).launch {
                val type = ExpenseType(name = name)
                expenseDataBase!!.expenseTypeDao().insert(type)
            }
        }

        fun getExpenseDetails(context: Context, id: Long): LiveData<ExpenseWithExpenseType>?{
            if(expenseDataBase == null){
                expenseDataBase = initializeDB(context)
            }
            expense = expenseDataBase!!.expenseDao().findExpenseWithExpenseTypeById(id)
            return expense
        }


        fun getAllExpenses(context: Context): LiveData<List<Expense>>{
            if(expenseDataBase == null){
                expenseDataBase = initializeDB(context)
            }
            return expenseDataBase!!.expenseDao().getAllExpenses()
        }

        fun getAllTypes(context: Context): LiveData<List<ExpenseType>>{
            if(expenseDataBase == null){
                expenseDataBase = initializeDB(context)
            }
            return expenseDataBase!!.expenseTypeDao().getAll()
        }
    }

    }


