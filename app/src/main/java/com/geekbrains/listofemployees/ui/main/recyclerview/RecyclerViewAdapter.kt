package com.geekbrains.listofemployees.ui.main.recyclerview

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.listofemployees.domain.data.models.base.Employee

class RecyclerViewAdapter(private val itemClick: (Employee) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

    private var userList: MutableList<Employee> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newUsersList: List<Employee>) {
        userList.clear()
        userList.addAll(newUsersList)
        userList.sortBy { it.phoneNumber }
        userList.removeAt(0)
        userList.sortBy { it.name }
        userList.removeAt(0)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.createView(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), itemClick)
    }

    private fun getItem(position: Int): Employee = userList[position]

    override fun getItemCount() = userList.size
}