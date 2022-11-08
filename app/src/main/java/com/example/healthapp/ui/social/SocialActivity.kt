package com.example.healthapp.ui.social

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.healthapp.MainActivity
import com.example.healthapp.R
import com.example.healthapp.databinding.ActivityMainBinding

class SocialActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.social_friends)
    }

    public fun close(view : View) {
//        close(view);
//        setContentView(R.layout.activity_main)
        super.onBackPressed()
    }
}