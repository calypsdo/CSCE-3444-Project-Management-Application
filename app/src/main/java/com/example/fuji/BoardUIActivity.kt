package com.example.fuji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BoardsUIActivity : AppCompatActivity() {

    var BoardName: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boards_ui)

        //Setting up the first board fragment to show
         val initialFragment = BoardUIFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentHolder, initialFragment)
        transaction.commit()

        BoardName = intent.getStringExtra("boardName")
    }
}