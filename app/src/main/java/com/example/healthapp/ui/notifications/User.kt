package com.example.healthapp.ui.notifications


import android.content.Context
import android.util.Log
import android.os.*;
import kotlin.collections.*;
import com.google.gson.*;
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader

// 0, 1, and 2
enum class Gender {
    Male, Female, Other
}

class User(name: String, age: Int, gender: Int, heightFt: Int, heightIn: Int, weight: Int) {

    lateinit var name: String
    var age = 0

    //var activityLevel = 0
    var weight = 0
    var heightFt = 0
    var heightIn = 0
    var gender = 0

    init {
        this.name = name
        this.age = age
        this.gender = gender
        this.heightFt = heightFt
        this.heightIn = heightIn
        this.weight = weight
        //this.activityLevel = activityLevel
    }

    companion object {
        private val TAG = "HEALTH010106"
        private val fileName = "userProfile.json"

        val dummyUser = User("", 0, 0, 0, 0, 0)

        fun updateUser(context: Context, user: User) {
            saveUser(context, user)
        }

        fun deleteUser(context: Context) {
            // Delete json file
            val fileDir = context.filesDir.absolutePath
            try {
                val file = File(fileDir, fileName)
                val deleted = file.delete()
            } catch (e: FileNotFoundException) {
                Log.i(TAG, "Cannot delete nonexistent user.")
                return
            }
        }

        fun saveUser(context: Context, user: User) {
            val filePath = context.filesDir

            // Serialize
            val gson = Gson()
            val str = gson.toJson(user)

            // Get file & write text
            val outFile = File(filePath, fileName)
            outFile.writeText(str)
            Log.i(TAG, "User successfully written to $fileName.")
        }

        fun getSavedUser(context: Context): User {
            val filePath = context.filesDir
            val gson = Gson()

            // Read file
            try {
                val reader = FileReader(filePath.absolutePath + "/" + fileName)
                Log.i(TAG, filePath.absolutePath + "/" + fileName)

                return gson.fromJson<User>(reader, User::class.java)
            } catch (e: FileNotFoundException) {
                Log.i(TAG, "Cannot read saved meals. $fileName not found.")
                return dummyUser
            }
        }
    }
}