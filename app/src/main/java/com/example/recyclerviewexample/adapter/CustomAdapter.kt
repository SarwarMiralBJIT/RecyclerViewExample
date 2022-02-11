package com.example.recyclerviewexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.Model.Task
import com.example.recyclerviewexample.R

class CustomAdapter (private val taskList:ArrayList<Task>): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]

        holder.taskTitle.text = task.title
        holder.date.text = task.date
        holder.firstTask.text = task.firstTask
        holder.secondTask.text = task.SecondTask

    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){
        val taskTitle:TextView = itemView.findViewById<TextView>(R.id.taskTitle)
        val date:TextView = itemView.findViewById<TextView>(R.id.date)
        val firstTask:TextView = itemView.findViewById<TextView>(R.id.firstTask)
        val secondTask:TextView = itemView.findViewById<TextView>(R.id.secondTask)
    }

}