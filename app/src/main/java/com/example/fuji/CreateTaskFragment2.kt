package com.example.fuji

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val ARG_PARAM1 = "boardName"

class CreateTaskFragment2 : Fragment() {
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////START OF FIREBASE///////////////////////////////////////////

    private val db = Firebase.firestore
    private var boardName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            boardName = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.create_task_fragment, container, false)
    }
    companion object {
        fun newInstance(param1: String) =
            CreateTaskFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    private val TAG = "DocSnippets"

    private fun addTask(boardName: String, taskName: String, dueDate: String, status: String, description: String) {
        val newTask = hashMapOf(
            "Title" to taskName,
            "Description" to description,
            "Due Date" to dueDate,
            "Status" to status
        )

        db.collection("boards").document(boardName).collection("Tasks")
            .document(taskName).set(newTask).addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully added")
                Toast.makeText(context, "Task Created", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener { e ->
                Log.w(TAG, "Error adding Board", e)
                Toast.makeText(context, "Task Creation Failed", Toast.LENGTH_SHORT).show()
            }
    }

    ///////////////////////////////////END OF FIREBASE/////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val taskNameInputView = view.findViewById<EditText>(R.id.create_task_task_name)
        val taskDueDateInputView = view.findViewById<EditText>(R.id.create_task_due_date)
        val taskStatusInputView = view.findViewById<EditText>(R.id.create_task_status)
        val taskDescriptionInputView = view.findViewById<EditText>(R.id.create_task_description)

        view.findViewById<Button>(R.id.create_task_back_button).setOnClickListener {
            var board: BoardsUIActivity = activity as BoardsUIActivity
            board.switchToBoardUIFragment()
        }

        view.findViewById<Button>(R.id.create_task_create_button).setOnClickListener {
            val boardNameRef = boardName.toString()
            val taskNameInput = taskNameInputView.text.toString().trim()
            val taskDueDatInput = taskDueDateInputView.text.toString().trim()
            val taskStatusInput = taskStatusInputView.text.toString().trim()
            val taskDescriptionInput = taskDescriptionInputView.text.toString().trim()

            if (taskNameInput.isEmpty() ||
                taskDueDatInput.isEmpty() ||
                taskStatusInput.isEmpty() ||
                taskDescriptionInput.isEmpty()) {
                Toast.makeText(context, "Please fill in the fields first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (taskNameInput.length > 20) {
                Toast.makeText(context, "Please give a task name of twenty characters or less", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // [FIREBASE ADD BOARD BEGIN]
            addTask(boardNameRef, taskNameInput, taskDueDatInput, taskStatusInput, taskDescriptionInput)
            // [FIREBASE ADD BOARD END]
            var board: BoardsUIActivity = activity as BoardsUIActivity
            Handler().postDelayed({board.switchToBoardUIFragment()}, 3000)
        }
    }

}