package com.example.fuji

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.fuji.models.Board

class BoardAdapter(private val boards: ArrayList<Board>) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val boardTitle: Button = itemView.findViewById(R.id.board_button)

        init {
            boardTitle.setOnClickListener {
                val position: Int = adapterPosition
                //Toast.makeText(itemView.context, "you clicked on item # ${position + 1}", Toast.LENGTH_SHORT).show()
                val intent = Intent(boardTitle.context, BoardsUIActivity::class.java)
                intent.putExtra("boardName", boardTitle.text)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                boardTitle.context.startActivity(intent)
            }
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                //Toast.makeText(itemView.context, "you clicked on item # ${position + 1}", Toast.LENGTH_SHORT).show()
                val intent = Intent(boardTitle.context, BoardsUIActivity::class.java)
                intent.putExtra("boardName", boardTitle.text)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                boardTitle.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val board = boards.get(position)
        holder.boardTitle.setText(board.Title)
    }

    override fun getItemCount(): Int {
        return boards.size
    }
}
