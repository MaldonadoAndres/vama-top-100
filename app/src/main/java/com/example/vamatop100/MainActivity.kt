package com.example.vamatop100

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vamatop100.databinding.ActivityMainBinding
import com.example.vamatop100.presentation.AlbumListFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager.beginTransaction().replace(R.id.container, AlbumListFragment()).commit()
    }
}