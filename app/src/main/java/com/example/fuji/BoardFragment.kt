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

class BoardFragment : Fragment(), BoardAdapter.OnItemClickListener {

    private var masterBoardList = arrayListOf<Board>()
    private var adapter: RecyclerView.Adapter<BoardAdapter.BoardViewHolder>? = null
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.board_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val boardListView = view.findViewById<RecyclerView>(R.id.board_list)

        db.collection("/boards").get().addOnSuccessListener { result ->
            for (document in result) {
                val title = document["Title"].toString()
                masterBoardList.add(Board(Title = title))
            }

            boardListView.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = BoardAdapter(masterBoardList, this@BoardFragment)
            }
        }

        //var adapter = BoardAdapter(masterBoardList, this)

        //boardListView.adapter = adapter
        //boardListView.layoutManager = LinearLayoutManager(activity)
        //boardListView.setHasFixedSize(true)


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

    override fun onItemClick(position: Int) {
        Toast.makeText(activity, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem: Board = masterBoardList[position]
        adapter?.notifyItemChanged(position)

        //DON"T ACTUALLY USE THIS REPLACE WITH SOMETHING TO GO TO NEXT ACTIVITY
        val test: BoardsActivity = activity as BoardsActivity
        test.switchToHelpFragment()
    }
}