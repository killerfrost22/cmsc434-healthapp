package com.example.healthapp

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.healthapp.databinding.ActivityMainBinding
import com.example.healthapp.MainData as HealthappMainData

class MainActivity : AppCompatActivity() {

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
    }
    
    fun playVideo(v: View){
        //var b = v.findViewById<Button>(R.id.starMed1)
        var hr = findViewById<TextView>(R.id.heartRate)
        var x = findViewById<VideoView>(R.id.videoView)

        x.setVideoURI(Uri.parse("android.resource://"+ getPackageName() +"/"+R.raw.medppt))
        hr.visibility=View.VISIBLE
        x.start()
    }
}
