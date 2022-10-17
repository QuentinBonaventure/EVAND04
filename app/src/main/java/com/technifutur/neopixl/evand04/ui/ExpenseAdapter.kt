package com.technifutur.neopixl.evand04.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technifutur.neopixl.evand04.databinding.ExpenseItemBinding
import com.technifutur.neopixl.evand04.model.Expense

class ExpenseAdapter(
    model: List<Expense>,
    private val onExpenseClicked: (Expense) -> Unit
): RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {
    private lateinit var binding: ExpenseItemBinding
    private var expenseList = mutableListOf<Expense>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        binding = ExpenseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ExpenseViewHolder(binding){
            position ->
            if(!expenseList.isNullOrEmpty()){
                onExpenseClicked(expenseList[position])
            }
        }
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val assetModel = expenseList[position]
        holder.bind(assetModel)
    }

    override fun onViewRecycled(holder: ExpenseViewHolder) {
        super.onViewRecycled(holder)
        holder.unbind()
    }

    override fun getItemCount(): Int {
        return  expenseList.size
    }





    class ExpenseViewHolder(
        private var viewBinding: ExpenseItemBinding,
        onExpenseClicked: (Int) -> Unit
    ): RecyclerView.ViewHolder(viewBinding.root){
        init {
            itemView.setOnClickListener {
                onExpenseClicked(adapterPosition)
            }
        }

        fun bind(model: Expense){
            viewBinding.nameTextView.text = model.name
            viewBinding.valueTextView.text = model.value.toString()
            viewBinding.dateTextView.text = model.date
            viewBinding.typeExpense.text = model.idType.toString()
        }

        fun unbind(){
            viewBinding.nameTextView.text = null
            viewBinding.valueTextView.text = null
            viewBinding.dateTextView.text = null
            viewBinding.typeExpense.text = null
        }
    }


}