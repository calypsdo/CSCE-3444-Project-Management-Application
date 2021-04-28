package com.example.fuji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration

class BoardsUIActivity : AppCompatActivity() {

    var boardName: String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boards_ui)

        //Setting up the first board fragment to show
        val initialFragment = BoardUIFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentHolder, initialFragment)
        transaction.commit()

        boardName = intent.getStringExtra("boardName")
    }

    fun switchToCreateTaskFragment() {
        val temp2fragment = CreateTaskFragment2.newInstance(boardName.toString())
        val taskTransaction = supportFragmentManager.beginTransaction()
        taskTransaction.replace(R.id.fragmentHolder, temp2fragment)
        taskTransaction.commit()
    }

    fun switchToBoardUIFragment() {
        val boardUIfragment = BoardUIFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentHolder, boardUIfragment)
        transaction.commit()
    }
}
