package com.example.fuji

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fuji.models.Board
import com.example.fuji.models.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.board_ui.*

class BoardUIFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<BoardUIAdapter.ViewHolder>? = null
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.board_ui, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val boardUIListView = view.findViewById<RecyclerView>(R.id.board_ui_list)

        //Grabs global variable from BoardsUIActivity and sets that text to the board title

        var boardName: String = (activity as BoardsUIActivity).BoardName.toString()
        board_ui_title.setText((activity as BoardsUIActivity).BoardName)

        // [START Firebase Get Tasks]
        db.collection("/boards").document(boardName).collection("Tasks").get().addOnSuccessListener { result ->
            val tasks = arrayListOf<Task>()

            for (document in result) {
                val title = document["Title"].toString()
                val description = document["Description"].toString()
                val dueDate = document["Due Date"].toString()
                val status = document["Status"].toString()

                tasks.add(Task(TaskTitle = title, DueDate = dueDate, Description = description, Status = status))
            }

            boardUIListView.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = BoardUIAdapter(tasks)
            }
        }
        // [END Firebase Get Tasks]

        //set on click listener for back button
        view.findViewById<ImageView>(R.id.board_ui_back_button).setOnClickListener {
            activity?.finish()
        }
    }
}