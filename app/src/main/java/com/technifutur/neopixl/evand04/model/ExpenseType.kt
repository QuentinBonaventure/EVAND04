package com.technifutur.neopixl.evand04.model

import androidx.room.*


@Entity
data class ExpenseType(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "expense_type_id")
    val expenseTypeId: Long = 0,
    val name: String
)

data class ExpenseWithExpenseType(
    @Embedded var expense: Expense,
    @Relation(
        parentColumn = "expense_id",
        entityColumn = "expense_type_id"
    )
    val expenseTypes: ExpenseType
)
