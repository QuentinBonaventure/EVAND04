package com.technifutur.neopixl.evand04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.technifutur.neopixl.evand04.databinding.ActivityAddExpenseBinding
import com.technifutur.neopixl.evand04.model.Expense
import com.technifutur.neopixl.evand04.model.ExpenseType

class AddExpense : AppCompatActivity() {


    private lateinit var types: List<ExpenseType>
    var saveId: ExpenseType? = null

    private var selectedTypes: ArrayList<ExpenseType> = arrayListOf()
    lateinit var binding: ActivityAddExpenseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ExpenseRepository.getAllTypes(this).observe(this){
            this.types = it
        }


        binding.typeList.setOnClickListener {
            val array = types.map { it.name }.toTypedArray()

            val builder = AlertDialog.Builder(this)
            val tmpTypes: ArrayList<ExpenseType> = arrayListOf()
            tmpTypes.addAll(selectedTypes)
            builder.setTitle("Choose a type")
            builder.setItems(array){
                _,position ->
                tmpTypes.add(types[position])
                saveId = types[position]
                binding.typeText.setText("${array[position]}")


            }


            builder.setPositiveButton("Ok"){ dialog, wich ->
        selectedTypes = tmpTypes
                }
                val dialog = builder.create()
            dialog.show()
            }
        }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_expense, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if(id == R.id.saveExpense){
            val name = binding.nameText.text.toString()
            val value = binding.valueText.text.toString().toFloatOrNull()

            val type = this.saveId?.expenseTypeId

            val date = binding.dateText.text.toString()
            if(name.isNotBlank() && value != null && type != null ){

                ExpenseRepository.insertExpense(this,name,date,value, type.toLong() )
                finish()

            }else{
                Toast.makeText(this,"Invalid Data", Toast.LENGTH_SHORT).show()
            }


        }
        return super.onOptionsItemSelected(item)
    }
}