package com.example.fuji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TaskViewActivity : AppCompatActivity() {

    var BoardName: String?=null
    var TaskDescription: String?=null
    var TaskDueDate: String?=null
    var TaskName: String?=null
    var TaskStatus: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boards_ui)

        //Setting up the first board fragment to show
        val initialFragment = Task_fragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentHolder, initialFragment)
        transaction.commit()

        TaskName = intent.getStringExtra("taskName")
        TaskDueDate = intent.getStringExtra("taskDueDate")
        TaskDescription = intent.getStringExtra("taskDescription")
        TaskStatus = intent.getStringExtra("taskStatus")
    }
}