package com.example.fuji

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fuji.models.Board
import kotlinx.android.synthetic.main.board_item.view.*

class BoardAdapter(private val boardList: List<Board>, private val listener: BoardFragment) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardAdapter.BoardViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.board_item, parent, false)
        return BoardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
            val currentBoard = boardList[position]
            holder.textView.text = currentBoard.Title
        }

    override fun getItemCount(): Int {
        return boardList.size
    }

    inner class BoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val textView: TextView = itemView.board_name

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}