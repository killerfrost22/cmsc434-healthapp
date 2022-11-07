package com.example.healthapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.*
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.healthapp.databinding.ActivityMainBinding
import com.example.healthapp.MainData as HealthappMainData

import java.text.SimpleDateFormat
import java.util.*
import android.view.MenuInflater

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    lateinit var curCalendar: Calendar


    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener

    var finalDate = 0L
    var finalTime = 0L

    private lateinit var binding: ActivityMainBinding
    public var TODAY =com.example.healthapp.MainData()
    public val YDAY = com.example.healthapp.MainData().yesterday()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        supportActionBar?.hide()

//        dateCur.setOnClickListener(this)
//        timeCur.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater : MenuInflater = getMenuInflater()
        inflater.inflate(R.menu.top_nav_menu, menu)
        return true
    }

    private fun updateTime() {
        //Mon, 5 Jan 2020
        val myformat = "h:mm a"
        val sdf = SimpleDateFormat(myformat, Locale.US)
        finalTime = curCalendar.time.time
//        timeCur.setText(sdf.format(curCalendar.time))

    }

    override fun onClick(v: View) {
        when (v.id) {
//            R.id.dateCur -> {
//                setListener()
//            }
        }
    }

    private fun setTimeListener() {
        curCalendar = Calendar.getInstance()

        timeSetListener =
            TimePickerDialog.OnTimeSetListener() { _: TimePicker, hourOfDay: Int, min: Int ->
                curCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                curCalendar.set(Calendar.MINUTE, min)
                updateTime()
            }

        val timePickerDialog = TimePickerDialog(
            this, timeSetListener, curCalendar.get(Calendar.HOUR_OF_DAY),
            curCalendar.get(Calendar.MINUTE), false
        )
        timePickerDialog.show()
    }


            private fun setListener() {
                curCalendar = Calendar.getInstance()

                dateSetListener =
                    DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                        curCalendar.set(Calendar.YEAR, year)
                        curCalendar.set(Calendar.MONTH, month)
                        curCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        updateDate()

                    }

                val datePickerDialog = DatePickerDialog(
                    this, dateSetListener, curCalendar.get(Calendar.YEAR),
                    curCalendar.get(Calendar.MONTH), curCalendar.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.datePicker.minDate = System.currentTimeMillis()
                datePickerDialog.show()
            }

            private fun updateDate() {

                val ourFormat = "EEE, d MMM yyyy"
                val dateFormat = SimpleDateFormat(ourFormat)
                finalDate = curCalendar.time.time
//                dateCur.setText(dateFormat.format(curCalendar.time))

//                timeInptLay.visibility = View.VISIBLE

            }
}