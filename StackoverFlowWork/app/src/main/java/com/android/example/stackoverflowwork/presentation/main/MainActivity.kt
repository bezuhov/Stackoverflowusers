package com.android.example.stackoverflowwork.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.example.stackoverflowwork.R

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}