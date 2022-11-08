package com.example.healthapp.ui.notifications
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import com.example.healthapp.MainActivity
import com.example.healthapp.MainData
import com.example.healthapp.R
import com.example.healthapp.databinding.ActivityMainBinding
import com.example.healthapp.ui.social.SocialActivity


class RegisterUserActivity : AppCompatActivity() {

    private lateinit var mGenderRadioButtonGroup : RadioGroup
    private lateinit var mUserContinueButton : Button

    private lateinit var mUserNameEdit : EditText
    private lateinit var mUserAgeEdit : EditText
    private lateinit var mUserHeightFtEdit : EditText
    private lateinit var mUserHeightInEdit : EditText
    private lateinit var mUserWeightEdit : EditText

    private val gender: Gender
        get() {

            when (mGenderRadioButtonGroup!!.checkedRadioButtonId) {
                R.id.genderMButton -> {
                    return Gender.Male
                }
                R.id.genderFButton -> {
                    return Gender.Female
                }
                else -> {
                    return Gender.Other
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        // Bar Title
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Create Profile"
        }

        mGenderRadioButtonGroup = findViewById(R.id.genderRadioGroup)
        mUserContinueButton = findViewById(R.id.userContinueButton)
        mUserNameEdit = findViewById(R.id.userNameEdit)
        mUserAgeEdit = findViewById(R.id.userAgeEdit)
        mUserHeightFtEdit = findViewById(R.id.userHeightFtEdit)
        mUserHeightInEdit = findViewById(R.id.userHeightInEdit)
        mUserWeightEdit = findViewById(R.id.userWeightEdit)

        // Save user data and move to home page
        mUserContinueButton.setOnClickListener {
            val name = mUserNameEdit.text.toString()
            val age = mUserAgeEdit.text.toString().toIntOrNull()
            val heightFt = mUserHeightFtEdit.text.toString().toIntOrNull()
            val heightIn = mUserHeightInEdit.text.toString().toIntOrNull()
            val weight = mUserWeightEdit.text.toString().toIntOrNull()
            val selectedGender = this.gender.ordinal

            // Value checks
            if (name.equals("") || age == null || heightFt == null || heightIn == null || weight == null
                || age <= 0 || heightFt < 0 || heightIn < 0 || heightIn >= 12 || weight <= 0) {
                Toast.makeText(applicationContext, "Please make sure all of your data is valid.",
                    Toast.LENGTH_LONG).show()
            } else {
                val user = User(name, age, selectedGender, heightFt, heightIn, weight)
                var userDetails =  MainData();
                userDetails.name = name;
                userDetails.age  = age;
                userDetails.setHeight(heightFt, heightIn);
                userDetails.weight = weight

                val startHome = Intent(this, MainActivity::class.java)
                startActivity(startHome)
            }
        }
    }

    fun toMain(view: View) {
        val intent = Intent(this, SocialActivity::class.java)
        startActivity(intent)
    }
}