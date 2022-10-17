package com.technifutur.neopixl.evand04.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.technifutur.neopixl.evand04.dao.ExpenseDao
import com.technifutur.neopixl.evand04.dao.ExpenseTypeDao
import com.technifutur.neopixl.evand04.model.Converters
import com.technifutur.neopixl.evand04.model.Expense
import com.technifutur.neopixl.evand04.model.ExpenseType

@Database(entities = arrayOf(Expense::class,ExpenseType::class), version = 2, exportSchema = true)
@TypeConverters(Converters::class)
abstract class ExpenseDataBase: RoomDatabase() {

  abstract fun expenseDao(): ExpenseDao
  abstract fun expenseTypeDao(): ExpenseTypeDao

  companion object{
   @Volatile
   private var sharedInstance: ExpenseDataBase? = null

   fun getDB(context: Context): ExpenseDataBase{
    if(sharedInstance != null) return sharedInstance!!
    synchronized(this){
     sharedInstance = Room
      .databaseBuilder(context, ExpenseDataBase::class.java, "expense.db")
      .fallbackToDestructiveMigration()
      .build()
     return sharedInstance!!
    }
   }
  }
}