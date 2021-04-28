package com.example.fuji

import android.app.AppComponentFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.board_ui.*
import kotlinx.android.synthetic.main.task_view_fragment_prototype.*

class Task_fragment : Fragment() {
    private val db = Firebase.firestore

    fun readFireStore() {
        db.collection("boards")//.document("Fuji").collection("categories").document("doing").collection("tasks").document("task first")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
    private val TAG = "DocSnippets"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.task_view_fragment_prototype, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val boardListView = view.findViewById<RecyclerView>(R.id.board_list)

        task_title.setText((activity as TaskViewActivity).TaskName)
        task_due_date.setText((activity as TaskViewActivity).TaskDueDate)
        task_desc_text.setText((activity as TaskViewActivity).TaskDescription)
        task_checklist_title.setText((activity as TaskViewActivity).TaskStatus)


        view.findViewById<ImageView>(R.id.task_back_button).setOnClickListener {
            activity?.finish()
        }

    }
}

