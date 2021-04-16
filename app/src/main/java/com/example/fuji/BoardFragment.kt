package com.example.fuji

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fuji.models.Board
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BoardFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<BoardAdapter.ViewHolder>? = null
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.board_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val boardListView = view.findViewById<RecyclerView>(R.id.board_list)

        // [START Firebase Get Boards]
        db.collection("/boards").get().addOnSuccessListener { result ->
            val boards = arrayListOf<Board>()

            for (document in result) {
                val title = document["Title"].toString()
                boards.add(Board(Title = title))
            }

            boardListView.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = BoardAdapter(boards)
            }
        }
        // [END Firebase Get Boards]

        view.findViewById<ImageView>(R.id.add_board_icon).setOnClickListener {
            val createBoard: BoardsActivity = activity as BoardsActivity
                createBoard.switchToCreateBoardFragment()
        }

        view.findViewById<ImageView>(R.id.logout_button).setOnClickListener {
            activity?.finish()
        }

        view.findViewById<ImageView>(R.id.info_icon).setOnClickListener {
            val helpBoard: BoardsActivity = activity as BoardsActivity
                helpBoard.switchToHelpFragment()
        }
    }
}