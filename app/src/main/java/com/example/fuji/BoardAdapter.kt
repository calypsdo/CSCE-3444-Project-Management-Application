package com.example.fuji

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.fuji.models.Board


class BoardAdapter(private val boardList: List<Board>, private val listener: BoardFragment) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val boardTitle: Button = itemView.findViewById(R.id.board_button)

        init {
            boardTitle.setOnClickListener {
                val position: Int = adapterPosition
                //Toast.makeText(itemView.context, "you clicked on item # ${position + 1}", Toast.LENGTH_SHORT).show()
            }
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                //Toast.makeText(itemView.context, "you clicked on item # ${position + 1}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)
        return ViewHolder(v)

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
}

