package com.example.fuji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BoardsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boards)

        val tempfragment = BoardFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.board_frame, tempfragment)
        transaction.commit()
    }

    fun switchToCreateBoardFragment() {
        val temp2fragment = CreateBoardFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.board_frame, temp2fragment)
        transaction.commit()
    }

    fun switchToBoardFragment() {
        val boardfragment = BoardFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.board_frame, boardfragment)
        transaction.commit()
    }
}