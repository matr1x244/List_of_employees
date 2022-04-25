package com.geekbrains.listofemployees.ui.recyclerview

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.listofemployees.domain.Employee
import com.geekbrains.listofemployees.domain.EmployeesEntity

class RecyclerViewAdapter: RecyclerView.Adapter<ViewHolder>() {

    private var userList: MutableList<EmployeesEntity> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newUsersList: List<EmployeesEntity>) {
        userList.clear()
        userList.addAll(newUsersList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.createView(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): EmployeesEntity = userList[position]

    override fun getItemCount() = userList.size
}