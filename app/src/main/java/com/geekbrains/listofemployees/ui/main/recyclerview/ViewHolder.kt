package com.geekbrains.listofemployees.ui.main.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.listofemployees.R
import com.geekbrains.listofemployees.databinding.RecyclerItemListEmployeeBinding
import com.geekbrains.listofemployees.domain.data.models.base.Employee

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = RecyclerItemListEmployeeBinding.bind(itemView)

    companion object {
        fun createView(parent: ViewGroup): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item_list_employee, parent, false)
            return ViewHolder(view)
        }
    }

    fun bind(item: Employee, listener: Employee.() -> Unit) {
        binding.itemName.text = item.name
        binding.itemPhone.text = item.phoneNumber
        binding.imageButtonAddHistory.setOnClickListener {
            listener.invoke(item)
        }
        binding.root.setOnClickListener {
            listener.invoke(item)
        }
    }

}