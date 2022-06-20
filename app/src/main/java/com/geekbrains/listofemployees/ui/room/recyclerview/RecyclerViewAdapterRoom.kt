package com.geekbrains.listofemployees.ui.room.recyclerview

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.listofemployees.domain.data.models.base.Employee
import com.geekbrains.listofemployees.domain.data.models.room.EmployeeEntityRoom

class RecyclerViewAdapterRoom(private val itemClick: (EmployeeEntityRoom) -> Unit) :
    RecyclerView.Adapter<ViewHolderRoom>() {

    private var userList: MutableList<EmployeeEntityRoom> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newUsersList: List<EmployeeEntityRoom>) {
        userList.clear()
        userList.addAll(newUsersList)
        userList.sortBy { it.phoneNumber }
        userList.removeAt(0)
        userList.sortBy { it.name }
        userList.removeAt(0)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRoom {
        return ViewHolderRoom.createView(parent)
    }

    override fun onBindViewHolder(holder: ViewHolderRoom, position: Int) {
        holder.bind(getItem(position), itemClick)
    }

    private fun getItem(position: Int): EmployeeEntityRoom = userList[position]

    override fun getItemCount() = userList.size
}