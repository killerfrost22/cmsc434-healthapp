package com.example.healthapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import java.io.File
import android.content.Context
import android.util.Log
import java.io.IOException
import java.nio.charset.Charset

class AddExercise: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_activity)

        // Bar Title
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Add Activity"
        }

        val nameOfActivity = findViewById<EditText>(R.id.activityName)
        val duration = findViewById<EditText>(R.id.duration)
        val date = findViewById<EditText>(R.id.activityDate)
        val weights = findViewById<EditText>(R.id.weight)
        val reps = findViewById<EditText>(R.id.reps)
        val button = findViewById<Button>(R.id.SubmitActivity)

        val spinner: Spinner = findViewById(R.id.exerciseType)
        ArrayAdapter.createFromResource(
            this,
            R.array.exercise_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }


        button.setOnClickListener{
            val name = nameOfActivity.text.toString()
            val durationString = duration.text.toString().toIntOrNull()
            val weightString = weights.text.toString().toIntOrNull()
            val repString = reps.text.toString().toIntOrNull()
            val spinnerSelect = spinner.selectedItem.toString()
            val dateString = date.text.toString()

            if (name.equals("") || dateString.equals("") || durationString == null || weightString == null
                || repString == null || spinnerSelect.equals("")) {
                Toast.makeText(applicationContext, "There is an invalid value in your activity entry.",
                    Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val newActivity = ActivityT(name,dateString,spinnerSelect,durationString,weightString,repString)
            ActivityT.addActivity(applicationContext, newActivity)
            finish()
        }


    }

    companion object {
        private val TAG = "HEALTHAPP"
    }
}