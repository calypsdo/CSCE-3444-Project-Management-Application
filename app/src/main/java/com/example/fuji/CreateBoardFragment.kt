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
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



class CreateBoardFragment : Fragment() {

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////START OF FIREBASE///////////////////////////////////////////

    private val db = Firebase.firestore

    private fun addBoard(boardName: String) {
        val newBoard = hashMapOf(
                "Title" to boardName
        )

        db.collection("boards").document(boardName).set(newBoard)
                .addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot successfully added")
                    Toast.makeText(context, "Board Created", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { e ->
                    Log.w(TAG, "Error adding Board", e)
                    Toast.makeText(context, "Board Creation Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private val TAG = "DocSnippets"
    ///////////////////////////////////END OF FIREBASE/////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.create_board_fragment, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val boardNameInputView = view.findViewById<EditText>(R.id.create_board_entry)

        view.findViewById<ImageView>(R.id.create_board_back_button).setOnClickListener {
            var board: BoardsActivity = activity as BoardsActivity
            board.switchToBoardFragment()
        }

        view.findViewById<Button>(R.id.create_board_button).setOnClickListener {
            val boardNameInput = boardNameInputView.text.toString().trim()

            if (boardNameInput.isEmpty()) {
                Toast.makeText(context, "Please give the board a name first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (boardNameInput.length > 20) {
                Toast.makeText(context, "Please give a board name of twenty characters or less", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // [FIREBASE ADD BOARD BEGIN]
            addBoard(boardNameInput)
            // [FIREBASE ADD BOARD END]
            var board: BoardsActivity = activity as BoardsActivity
            Handler().postDelayed({board.switchToBoardFragment()}, 3000)
        }
    }
}
