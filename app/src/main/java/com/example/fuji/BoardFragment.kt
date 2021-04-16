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
