
package com.example.pract61_bodrov_pr32.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.pract61_bodrov_pr32.R
import org.json.JSONObject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etLogin = findViewById<EditText>(R.id.etLogin)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        val preferences = getSharedPreferences("UserData", MODE_PRIVATE)


        btnLogin.setOnClickListener {

            val login = etLogin.text.toString()
            val password = etPassword.text.toString()

            if (login.isEmpty() || password.isEmpty()) {

                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()

            } else {

                val user = JSONObject()
                user.put("login", login)

                preferences.edit()
                    .putString("user", user.toString())
                    .apply()

                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)



            }
        }

    }
}
