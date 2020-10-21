package com.stud.awra.tesktaskuserapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private val nacController by lazy { findNavController(R.id.nav_host_container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if (!nacController.popBackStack()) super.onBackPressed()
    }
}