package com.infojunks.eatoestestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val name=intent.getStringExtra("name")
        val token=intent.getStringExtra("token")
        val editText1=findViewById<TextView>(R.id.textView11)
        val editText2=findViewById<TextView>(R.id.textView12)
        val editText3=findViewById<TextView>(R.id.textView13)
        val editText4=findViewById<TextView>(R.id.textView14)

        editText1.text="Hello"
        editText2.text="${name}"
        editText3.text="Your token is"
        editText4.text="${token}"
    }
}