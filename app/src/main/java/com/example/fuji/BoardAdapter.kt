package com.example.fuji

import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.fuji.models.Board
import com.google.firebase.firestore.auth.User

class BoardAdapter(private val boards: ArrayList<Board>) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var boardTitle: TextView = itemView.findViewById(R.id.board_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.board_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val board = boards.get(position)
        holder.boardTitle.setText(board.Title)
        }

    override fun getItemCount(): Int {
        return boards.size
    }

    fun bind(position: Int, recyclerViewClickListener: IItemClickListener) {
    }


}