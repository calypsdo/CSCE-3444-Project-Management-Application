package com.example.fuji

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.fuji.models.Board
import com.example.fuji.models.Task

class BoardUIAdapter(private val tasks: ArrayList<Task>) : RecyclerView.Adapter<BoardUIAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskTitle: Button = itemView.findViewById(R.id.task_button)

        init {
            taskTitle.setOnClickListener {
                val position: Int = adapterPosition
            }
            itemView.setOnClickListener {
                val position: Int = adapterPosition
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.board_ui_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks.get(position)
        holder.taskTitle.setText(task.TaskTitle)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}
