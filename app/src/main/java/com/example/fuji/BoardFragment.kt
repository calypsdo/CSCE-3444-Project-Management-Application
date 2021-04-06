package com.example.fuji

import android.app.Activity
import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fuji.models.Board

class BoardFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<BoardAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.board_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val boardlist = view.findViewById<RecyclerView>(R.id.board_list)
        val temp = arrayListOf<Board>()
        val temp2 = Board("testing")
        val temp3 = Board("another test")
        temp.add(temp2)
        temp.add(temp3)
        boardlist.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = BoardAdapter(temp)
        }


        view.findViewById<ImageView>(R.id.add_board_icon).setOnClickListener {
            var createBoard: BoardsActivity = activity as BoardsActivity
                createBoard.switchToCreateBoardFragment()
        }
    }


    }