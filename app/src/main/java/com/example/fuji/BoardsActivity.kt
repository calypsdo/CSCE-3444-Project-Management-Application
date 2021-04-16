package com.example.fuji

import android.app.Notification
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class BoardsActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boards)

        //Setting up the first board fragment to show
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

    fun switchToHelpFragment() {
        val helpFragment = HelpFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.board_frame, helpFragment)
        transaction.commit()
    }
}