package com.geekbrains.listofemployees.ui.room.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.listofemployees.R
import com.geekbrains.listofemployees.databinding.RecyclerItemRoomListEmployeeBinding
import com.geekbrains.listofemployees.domain.data.models.base.Employee
import com.geekbrains.listofemployees.domain.data.models.room.HistoryEntity

class ViewHolderRoom(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = RecyclerItemRoomListEmployeeBinding.bind(itemView)

    companion object {
        fun createView(parent: ViewGroup): ViewHolderRoom {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item_room_list_employee, parent, false)
            return ViewHolderRoom(view)
        }
    }

    fun bind(item: Employee, listener: Employee.() -> Unit) {
        binding.itemName.text = item.name
        binding.itemPhone.text = item.phoneNumber
        binding.imageButtonDeleteHistory.setOnClickListener {
            listener.invoke(item)
        }
        binding.root.setOnClickListener {
            listener.invoke(item)
        }
    }

}