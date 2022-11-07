package com.example.healthapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Text : Fragment() {
    // inflate the layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(R.layout.social_main, container, false)!!

    fun moodChoice(view: View){
        val id = view.id;
        if(id == R.id.awesome) {
            val textView: TextView = findViewById(R.id.empty) as TextView;
            textView.text="I'm glad you have an awesome day!";
        }
        if(id == R.id.alright) {
            val textView: TextView = findViewById(R.id.empty) as TextView;
            textView.text="Want any change to the existing schedule?";
        }
        if(id == R.id.not_great) {
            val textView: TextView = findViewById(R.id.empty) as TextView;
            textView.text="Let's try to adjust the routine.";
        }
        setContentView(R.layout.social_friends);
    }
}